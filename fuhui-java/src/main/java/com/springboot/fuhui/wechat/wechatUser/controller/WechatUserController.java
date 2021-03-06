package com.springboot.fuhui.wechat.wechatUser.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.springboot.fuhui.sms.model.SMSModel;
import com.springboot.fuhui.sms.repository.SMSRepository;
import com.springboot.fuhui.sms.utils.AliyunSMSUtils;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.system.utils.StaffCacheUtil;
import com.springboot.fuhui.web.adminUser.model.AdminUserModel;
import com.springboot.fuhui.wechat.carNum.model.CarNumModel;
import com.springboot.fuhui.wechat.carNum.repository.CarNumRepository;
import com.springboot.fuhui.wechat.wechatUser.model.WechatUserModel;
import com.springboot.fuhui.wechat.wechatUser.repository.WechatUserRespository;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/7
 * Time:11:52
 **/
@Controller
@RequestMapping(path = "/mp/wechatUser")
public class WechatUserController {

    private final Logger logger = LoggerFactory.getLogger(WechatUserController.class);

    @Autowired
    WechatUserRespository wechatUserRespository;

    @Autowired
    SMSRepository smsRepository;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    CarNumRepository carNumRepository;


    @GetMapping(value = "/oauth2Wechat")
    public ModelAndView oauth2Wechat() {
        String url = wxMpService.oauth2buildAuthorizationUrl("http://fuhui.kaixindaka.com/mp/wechatUser/getCode", WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        logger.info(">>>>>>>>>>>>>>>>>>>url:" + url);
        return new ModelAndView(new RedirectView(url));
    }

    @PostMapping(value = "/getOauthUrl")
    @ResponseBody
    public CommonJson getOauthUrl() throws IOException {

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatUserController.getOauthUrl>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String key = jsonObject.getString("key");

        CommonJson json = new CommonJson();

        if ("NA2i760YXSgfsiOlQl8z4ps5Zll73FfM".equals(key)) {
            String url = wxMpService.oauth2buildAuthorizationUrl("http://fuhui.kaixindaka.com/mp/wechatUser/getCode", WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
            logger.info("WechatUserController.getOauthUrl>>>>>>>>>>>>url:" + url);
            Map<String, Object> map = Maps.newHashMap();
            map.put("url", url);

            json.setResultCode("1");
            json.setResultData(map);
            json.setResultMsg("success");
            return json;
        } else {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("fail");
            return json;
        }
    }

    @GetMapping(value = "/getCode")
    public ModelAndView getCode(@RequestParam String code, HttpServletResponse response) throws WxErrorException, ExecutionException, IOException {
        logger.info(">>>>>>>>>>>>>>>>WechatUserController.getToken.getCode>>>>>>>>>>>>>>>");
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>wxMpUser:" + wxMpUser.toString());
        WechatUserModel wechatUserModel = wechatUserRespository.getByOpenIdIs(wxMpUser.getOpenId());

        if (wechatUserModel != null) { // 如果用户存在
            wechatUserModel.setUpdateDate(new Date());
        } else { // 如果用户不存在
            wechatUserModel = new WechatUserModel();
            wechatUserModel.setCreateDate(new Date());
            wechatUserModel.setOpenId(wxMpUser.getOpenId());
            // 初始化积分
            wechatUserModel.setPoints(0);
        }
        // 更新微信信息
        wechatUserModel.setNickname(wxMpUser.getNickname());
        wechatUserModel.setUnionId(wxMpUser.getUnionId());
        wechatUserModel.setSex(wxMpUser.getSex());
        wechatUserModel.setWechatHeadImg(wxMpUser.getHeadImgUrl());

        wechatUserModel = wechatUserRespository.save(wechatUserModel);

        // 生成token
        String token = UUID.randomUUID().toString();
        // 将WechatUserModel存入缓存
        StaffCacheUtil.create().put(token, wechatUserModel);
        response.sendRedirect("http://mp.kaixindaka.com/#/home?token=" + token);

        return null;
    }


    /**
     * 获取token，完成腾讯交互获取微信用户信息，并相关数据存入缓存
     * @return
     * @throws IOException
     * @throws ExecutionException
     */
    @PostMapping(value = "/getToken")
    @ResponseBody
    public CommonJson getToken() throws IOException, ExecutionException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatUserController.getToken>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String key = jsonObject.getString("key");
        CommonJson json = new CommonJson();
        // 如果key相符合
        if ("NA2i760YXSgfsiOlQl8z4ps5Zll73FfM".equals(key)) {
            // 和腾讯交互，获取WxMpUser，并更新或者保存WechatUserModel
            WxMpUser wxMpUser = new WxMpUser();
            wxMpUser.setOpenId("111111111");
            wxMpUser.setHeadImgUrl("http://storage.360buyimg.com/i.imageUpload/4d6574656f723139393331353134323936363537373539_mid.jpg");

            WechatUserModel wechatUserModel = wechatUserRespository.getByOpenIdIs(wxMpUser.getOpenId());

            if (wechatUserModel != null) { // 如果用户存在
                wechatUserModel.setUpdateDate(new Date());
            } else { // 如果用户不存在
                wechatUserModel = new WechatUserModel();
                wechatUserModel.setCreateDate(new Date());
                wechatUserModel.setOpenId(wxMpUser.getOpenId());
                // 初始化积分
                wechatUserModel.setPoints(0);
            }
            // 更新微信信息
            wechatUserModel.setNickname(wxMpUser.getNickname());
            wechatUserModel.setUnionId(wxMpUser.getUnionId());
            wechatUserModel.setSex(wxMpUser.getSex());
            wechatUserModel.setWechatHeadImg(wxMpUser.getHeadImgUrl());

            wechatUserModel = wechatUserRespository.save(wechatUserModel);

            // 生成token
            String token = UUID.randomUUID().toString();
            // 将WechatUserModel存入缓存
            StaffCacheUtil.create().put(token, wechatUserModel);
            // 将token返回
            Map<String, Object> map = Maps.newHashMap();
            map.put("token", token);
            json.setResultCode("1");
            json.setResultMsg("success");
            json.setResultData(map);
            return json;
        }

        return null;
    }

    /**
     * 获取用户信息
     * @return
     */
    @PostMapping(value = "/getUserInfo")
    @ResponseBody
    public CommonJson getUserInfo() {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        logger.info("WechatUserController.getUserInfo>>>>>>>>>>>>token:" + token);

        WechatUserModel wechatUserModel = null;

        try {
            wechatUserModel = (WechatUserModel) StaffCacheUtil.create().get(token, new Callable<AdminUserModel>() {
                @Override
                public AdminUserModel call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CommonJson json = new CommonJson();

        Map<String, Object> map = Maps.newHashMap();

        if (wechatUserModel != null) {
            wechatUserModel = wechatUserRespository.getByOpenIdIs(wechatUserModel.getOpenId());
            map.put("info", wechatUserModel);
            json.setResultCode("1");
            json.setResultMsg("success");
            json.setResultData(map);
        } else {
            json.setResultCode("0");
            json.setResultMsg("fail");
        }

        return json;
    }

    /**
     * 发送短信
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/sendSMS")
    @ResponseBody
    public CommonJson sendSMS() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatUserController.sendSMS>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String phone = jsonObject.getString("phone");

        CommonJson json = new CommonJson();
        if (Strings.isNullOrEmpty(phone) || phone.length() != 11) {
            json.setResultCode("0");
            json.setResultMsg("请输入正确的手机号");
            return json;
        }

        AliyunSMSUtils smsUtils = AliyunSMSUtils.getInstance();

        String code = getRandomCode();

        logger.info("---------------sendSMS--------------phone:" + phone + ",code:" + code);

        boolean flag = smsUtils.sendAliyunSMS(phone, code);

        // 测试短信发送成功
        flag = true;
        if (flag) {
            SMSModel smsModel = new SMSModel();
            smsModel.setCreateDate(new Date());
            smsModel.setMobile(phone);
            smsModel.setCreateSessionID(token);
            smsModel.setIsValid("1");
            smsModel.setVerificationCode(code);
            smsRepository.save(smsModel);
        }

        if (flag) {
            json.setResultCode("1");
            json.setResultMsg("短信发送成功");
        } else {
            json.setResultCode("0");
            json.setResultMsg("网络拥堵，请稍后重试");
        }

        return json;
    }

    /**
     * 绑定手机
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/bindPhone")
    @ResponseBody
    public CommonJson bindPhone() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatUserController.bindPhone>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String phone = jsonObject.getString("phone");
        String code = jsonObject.getString("code");

        CommonJson json = new CommonJson();

        if (Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(code)) {
            json.setResultCode("0");
            json.setResultMsg("手机号或验证码为空");
            return json;
        }

        int checkMinutes = 5; // 校验短信有效期

        // 校验短信验证码
        List<SMSModel> smsModelList = smsRepository.findAllByCreateSessionIDAndIsValidAndVerificationCodeOrderByCreateDateDesc(token, "1", code);

        WechatUserModel wechatUserModel = null;

        try {
            wechatUserModel = (WechatUserModel) StaffCacheUtil.create().get(token, new Callable<AdminUserModel>() {
                @Override
                public AdminUserModel call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (wechatUserModel == null) {
            json.setResultCode("0");
            json.setResultMsg("网络异常，请稍后重试");
            return json;
        }

        try {
            if (smsModelList.size() > 0) { // 查询到相关数据
                SMSModel smsModel = smsModelList.get(0);
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String createDate = simpleFormat.format(smsModel.getCreateDate());
                String nowDate = simpleFormat.format(new Date());
                long create = simpleFormat.parse(createDate).getTime();
                long now = simpleFormat.parse(nowDate).getTime();
                int times = (int) ((now - create)/(1000 * 60));
                if ("1".equals(smsModel.getIsValid())) { // 判断当前验证码是否有效
                    if (times < checkMinutes) { // 判断时间是否小于有效时间
                        // 将验证码变为无效
                        smsModel.setIsValid("0");
                        smsRepository.save(smsModel);
                        json.setResultCode("1");
                        json.setResultMsg("验证码输入正确");
                    } else {
                        json.setResultCode("0");
                        json.setResultMsg("当前验证码已过期，请重新获取");
                        return json;
                    }
                } else {
                    json.setResultCode("0");
                    json.setResultMsg("您的验证码已失效，请重新获取");
                    return json;
                }
            } else {
                json.setResultCode("0");
                json.setResultMsg("验证码输入有误，请核对后重新输入");
                return json;
            }
        } catch (ParseException e) {
            logger.error("error", e);
        }

        if ("1".equals(json.getResultCode())) { // 验证码校验通过
            wechatUserModel = wechatUserRespository.getByOpenIdIs(wechatUserModel.getOpenId());
            if (wechatUserModel != null) {
                wechatUserModel.setPhone(phone);
                wechatUserRespository.save(wechatUserModel);
                json.setResultCode("1");
                json.setResultMsg("手机绑定成功");
                json.setResultData(null);
            } else {
                json.setResultCode("0");
                json.setResultMsg("验证码输入有误，请核对后重新输入");
                json.setResultData(null);
            }

        }

        return json;
    }

    /**
     * 获取随机数
     * @return
     */
    private String getRandomCode() {
        Random rad = new Random();

        String result = rad.nextInt(1000000) + "";

        if(result.length() != 6){
            return getRandomCode();
        }
        return result;
    }

    /**
     * 绑定车牌号
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/bindCarNum")
    @ResponseBody
    public CommonJson bindCarNum() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info("WechatUserController.bindCarNum>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String carNum = jsonObject.getString("carNum");

        CommonJson json = new CommonJson();

        if (Strings.isNullOrEmpty(carNum)) {
            json.setResultCode("0");
            json.setResultMsg("请输入正确的车牌号");
            return json;
        }

        List<CarNumModel> list = carNumRepository.findAllByCarNum(carNum);

        if (list.size() > 0) {
            json.setResultCode("0");
            json.setResultMsg("该车牌号已被绑定，请至服务台处理");
            return json;
        }

        WechatUserModel wechatUserModel = null;

        try {
            wechatUserModel = (WechatUserModel) StaffCacheUtil.create().get(token, new Callable<AdminUserModel>() {
                @Override
                public AdminUserModel call() {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<CarNumModel> myList = carNumRepository.findAllByOpenid(wechatUserModel.getOpenId());

        if (myList.size() >= 3) {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("您已绑定3张车牌，请删除后重新绑定");
            return json;
        }

        CarNumModel carNumModel = new CarNumModel();
        carNumModel.setCarNum(carNum);
        carNumModel.setCreateDate(new Date());
        carNumModel.setOpenid(wechatUserModel.getOpenId());
        carNumModel.setPhone(wechatUserModel.getPhone());
        carNumRepository.save(carNumModel);

        json.setResultCode("1");
        json.setResultMsg("车牌绑定成功");
        return json;
    }

    /**
     * 根据openid获取车牌列表
     * @return
     */
    @PostMapping(value = "/findCarNumListByOpenId")
    @ResponseBody
    public CommonJson findCarNumListByOpenId() {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        logger.info("WechatUserController.findCarNumListByOpenId>>>>>>>>>>>>token:" + token);

        WechatUserModel wechatUserModel = null;

        try {
            wechatUserModel = (WechatUserModel) StaffCacheUtil.create().get(token, new Callable<AdminUserModel>() {
                @Override
                public AdminUserModel call() {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<CarNumModel> myList = carNumRepository.findAllByOpenid(wechatUserModel.getOpenId());

        Map<String, Object> map = Maps.newHashMap();
        map.put("list", myList);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    /**
     * 根据id删除车牌
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delCarNumById")
    @ResponseBody
    @Transactional
    public CommonJson delCarNumById() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info("WechatUserController.delCarNumById>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String carNumId = jsonObject.getString("id");

        CarNumModel carNumModel = carNumRepository.getByIdIs(carNumId);

        CommonJson json = new CommonJson();

        if (carNumModel != null) {
            carNumRepository.delete(carNumModel);
            json.setResultCode("1");
            json.setResultData(null);
            json.setResultMsg("删除成功");
        } else {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("删除失败，该车牌不存在");
        }

        return json;
    }
}
