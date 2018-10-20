package com.springboot.fuhui.wechat.appointment.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.system.utils.StaffCacheUtil;
import com.springboot.fuhui.web.adminUser.model.AdminUserModel;
import com.springboot.fuhui.wechat.appointment.model.AppointmentModel;
import com.springboot.fuhui.wechat.appointment.respository.AppointmentRespository;
import com.springboot.fuhui.wechat.card.model.CardModel;
import com.springboot.fuhui.wechat.card.model.CardUseLogModel;
import com.springboot.fuhui.wechat.card.respository.CardRepository;
import com.springboot.fuhui.wechat.card.respository.CardUseLogReponsitory;
import com.springboot.fuhui.wechat.onlineCourses.model.OnlineCourseModel;
import com.springboot.fuhui.wechat.onlineCourses.repository.OnlineCourseRepository;
import com.springboot.fuhui.wechat.wechatUser.model.WechatUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/20
 * Time:10:04
 **/
@RestController
@RequestMapping(path = "/mp/wechatAppointment")
public class AppointmentController {
    private final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    AppointmentRespository appointmentRespository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardUseLogReponsitory cardUseLogReponsitory;

    @Autowired
    OnlineCourseRepository onlineCourseRepository;

    /**
     * 查询篮球场预约人数
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/findBasketBallUsePeople")
    public CommonJson findBasketBallUsePeople() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AppointmentController.findBasketBallUsePeople>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String appointmentDate = jsonObject.getString("appointmentDate");

        String appointmentPeroid = jsonObject.getString("appointmentPeroid");

        List<AppointmentModel> list = appointmentRespository.findAllByAppointmentDateAndAppointmentPeroidAndFlag(appointmentDate, appointmentPeroid, "1");

        Map<String, Object> map = Maps.newHashMap();
        map.put("size", list.size());

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    /**
     * 保存篮球场预约
     * @return
     */
    @PostMapping(value = "/saveBasketBallAppointment")
    public CommonJson saveBasketBallAppointment() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AppointmentController.saveBasketBallAppointment>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String appointmentDate = jsonObject.getString("appointmentDate");

        String appointmentPeroid = jsonObject.getString("appointmentPeroid");

        String cardId = jsonObject.getString("cardId");

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

        List<AppointmentModel> list = appointmentRespository.findAllByAppointmentDateAndAppointmentPeroidAndFlag(appointmentDate, appointmentPeroid, "1");

        if (list.size() >= 4) {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("当前时间段已约满，请更换其他时间段");
            return json;
        }

        List<AppointmentModel> myList = appointmentRespository.findAllByOpenIdAndAppointmentDateAndAppointmentPeroidAndFlag(wechatUserModel.getOpenId(), appointmentDate, appointmentPeroid, "1");

        if (myList.size() > 0) {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("您已预约当前时间段，请核对后再约");
            return json;
        }

        CardModel cardModel = cardRepository.getByIdIs(cardId);

        if ("1".equals(cardModel.getStatus())) {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("该券已使用，请核对后再约");
            return json;
        }

        // 保存预约信息
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setOpenId(wechatUserModel.getOpenId());
        appointmentModel.setPhone(wechatUserModel.getPhone());
        appointmentModel.setCreateDate(new Date());
        appointmentModel.setStatus("0");
        appointmentModel.setFlag("1");
        appointmentModel.setAppointmentDate(appointmentDate);
        appointmentModel.setAppointmentPeroid(appointmentPeroid);
        appointmentModel.setCardId(cardId);
        appointmentModel.setType(cardModel.getType());

        appointmentModel = appointmentRespository.save(appointmentModel);

        // 更改卡券状态
        cardModel.setStatus("1");
        cardModel.setUseNum("0");
        cardModel.setUpdateDate(new Date());
        cardRepository.save(cardModel);

        // 增加卡券使用记录
        CardUseLogModel cardUseLogModel = new CardUseLogModel();
        cardUseLogModel.setCardId(cardId);
        cardUseLogModel.setCreateDate(new Date());
        cardUseLogModel.setOpenId(wechatUserModel.getOpenId());
        cardUseLogModel.setProductName(cardModel.getProductName());
        cardUseLogModel.setStatus("0");
        cardUseLogReponsitory.save(cardUseLogModel);

        Map<String, Object> map = Maps.newHashMap();
        map.put("info", appointmentModel);

        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("预约成功");
        return json;
    }

    /**
     * 查询线上课程预约人数
     * @return
     */
    @PostMapping(value = "/findOnlineUsePeople")
    public CommonJson findOnlineUsePeople() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AppointmentController.findOnlineUsePeople>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String courseId = jsonObject.getString("courseId");

        List<AppointmentModel> list = appointmentRespository.findAllByCourseIdAndFlag(courseId, "1");

        Map<String, Object> map = Maps.newHashMap();
        map.put("size", list.size());

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    /**
     * 保存线上课程预约
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/saveOnlineAppointment")
    public CommonJson saveOnlineAppointment() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AppointmentController.saveOnlineAppointment>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String courseId = jsonObject.getString("courseId");
        String cardId = jsonObject.getString("cardId");

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

        OnlineCourseModel onlineCourseModel = onlineCourseRepository.getByIdIs(courseId);

        List<AppointmentModel> list = appointmentRespository.findAllByCourseIdAndFlag(courseId, "1");

        List<AppointmentModel> myList = appointmentRespository.findAllByOpenIdAndCourseIdAndFlag(wechatUserModel.getOpenId(), courseId, "1");

        if (onlineCourseModel.getNum() <= list.size()) {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("该课程已满约，请更换其他课程");
            return json;
        }

        if (myList.size() > 0) {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("您已预约过该课程，请更换其他课程");
            return json;
        }

        CardModel cardModel = cardRepository.getByIdIs(cardId);

        if ("1".equals(cardModel.getStatus())) {
            json.setResultCode("0");
            json.setResultData(null);
            json.setResultMsg("该券已使用，请核对后再约");
            return json;
        }

        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setOpenId(wechatUserModel.getOpenId());
        appointmentModel.setCreateDate(new Date());
        appointmentModel.setCardId(cardId);
        appointmentModel.setCourseId(courseId);
        appointmentModel.setType(cardModel.getType());
        appointmentModel.setPhone(wechatUserModel.getPhone());
        appointmentModel.setStatus("0");
        appointmentModel.setFlag("1");
        appointmentRespository.save(appointmentModel);

        cardModel.setStatus("0");
        cardModel.setUpdateDate(new Date());
        cardModel.setUseNum(String.valueOf(Integer.parseInt(cardModel.getUseNum()) - 1));
        cardRepository.save(cardModel);

        // 增加卡券使用记录
        CardUseLogModel cardUseLogModel = new CardUseLogModel();
        cardUseLogModel.setCardId(cardId);
        cardUseLogModel.setCreateDate(new Date());
        cardUseLogModel.setOpenId(wechatUserModel.getOpenId());
        cardUseLogModel.setProductName(cardModel.getProductName());
        cardUseLogModel.setStatus("0");
        cardUseLogReponsitory.save(cardUseLogModel);

        json.setResultCode("1");
        json.setResultMsg("预约成功");

        return json;
    }
}
