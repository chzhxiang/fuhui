package com.springboot.fuhui.web.wehchatUser.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.wechat.points.model.PointsLogModel;
import com.springboot.fuhui.wechat.points.repository.PointsLogResponsitory;
import com.springboot.fuhui.wechat.wechatUser.model.WechatUserModel;
import com.springboot.fuhui.wechat.wechatUser.repository.WechatUserRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @program: fuhui
 * @description:
 * @author: weishiyao
 * @create: 2018-10-03 10:09
 **/
@RestController
@RequestMapping(path = "/admin/adminWechatUser")
public class AdminWechatUserController {
    private final Logger logger = LoggerFactory.getLogger(AdminWechatUserController.class);

    @Autowired
    WechatUserRespository wechatUserRespository;

    @Autowired
    PointsLogResponsitory pointsLogResponsitory;

    /**
     * 查询用户列表
     * @return
     */
    @PostMapping(value = "/getWechatUserList")
    public CommonJson getWechatUserList() throws IOException {

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info("AdminWechatUserController.getWechatUserList>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String phone = jsonObject.getString("phone");
        String pageNum = jsonObject.getString("pageNum");
        String pageSize = jsonObject.getString("pageSize");

        if (Strings.isNullOrEmpty(pageNum)) {
            pageNum = "1";
        }

        if (Strings.isNullOrEmpty(pageSize)) {
            pageSize = "10";
        }

        CommonJson json = new CommonJson();
        Page<WechatUserModel> page = null;

        if (Strings.isNullOrEmpty(phone)) {
            page = wechatUserRespository.findAll(new PageRequest(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
        } else {
            page = wechatUserRespository.getByPhoneIs(phone, new PageRequest(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("page", page);

        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }

    /**
     * 根据手机号查询用户列表
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/getWechatUserListByPhone")
    public CommonJson getWechatUserListByPhone() throws IOException {

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info("AdminWechatUserController.getWechatUserListByPhone>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String phone = jsonObject.getString("phone");
        String pageSize = jsonObject.getString("pageSize");

        if (Strings.isNullOrEmpty(pageSize)) {
            pageSize = "10";
        }

        CommonJson json = new CommonJson();

        Page<WechatUserModel> page = null;

        if (Strings.isNullOrEmpty(phone)) {
            page = wechatUserRespository.findAll(new PageRequest(0, Integer.parseInt(pageSize)));
        } else {
            page = wechatUserRespository.getByPhoneIs(phone, new PageRequest(0, Integer.parseInt(pageSize)));
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("page", page);

        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }

    /**
     * 解绑手机
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delPhone")
    public CommonJson delPhone() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info("AdminWechatUserController.delPhone>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");
        WechatUserModel wechatUserModel = wechatUserRespository.getByIdIs(id);
        wechatUserModel.setPhone("");
        wechatUserModel = wechatUserRespository.save(wechatUserModel);

        Map<String, Object> map = Maps.newHashMap();
        map.put("info", wechatUserModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }

    /**
     * 解绑车牌
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delCarNumber")
    public CommonJson delCarNumber() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info("AdminWechatUserController.delCarNumber>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");

        WechatUserModel wechatUserModel = wechatUserRespository.getByIdIs(id);
        wechatUserModel.setCarNumber("");
        wechatUserModel = wechatUserRespository.save(wechatUserModel);

        Map<String, Object> map = Maps.newHashMap();
        map.put("info", wechatUserModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }

    /**
     * 增加积分
     * @return
     */
    @PostMapping(value = "/addPoints")
    public CommonJson addPoints() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info("AdminWechatUserController.addPoints>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");
        String points = jsonObject.getString("points");

        // 增加用户积分
        WechatUserModel wechatUserModel = wechatUserRespository.getByIdIs(id);
        wechatUserModel.setPoints(new BigDecimal(wechatUserModel.getPoints()).add(new BigDecimal(points)).intValue());
        wechatUserModel = wechatUserRespository.save(wechatUserModel);

        // 增加积分变动日志
        PointsLogModel pointsLogModel = new PointsLogModel();
        pointsLogModel.setCreateDate(new Date());
        pointsLogModel.setOpenId(wechatUserModel.getOpenId());
        pointsLogModel.setPhone(wechatUserModel.getPhone());
        pointsLogModel.setPoints(Integer.parseInt(points));
        pointsLogModel.setType("0");
        pointsLogResponsitory.save(pointsLogModel);

        Map<String, Object> map = Maps.newHashMap();
        map.put("info", wechatUserModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }
}
