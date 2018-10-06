package com.springboot.fuhui.web.draw.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.web.draw.model.AdminDrawModel;
import com.springboot.fuhui.web.draw.responsitory.AdminDrawResponsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/3
 * Time:18:54
 **/
@RestController
@RequestMapping(path = "/admin/adminDraw")
public class AdminDrawController {
    private final Logger logger = LoggerFactory.getLogger(AdminDrawController.class);

    @Autowired
    AdminDrawResponsitory adminDrawResponsitory;

    @PostMapping(value = "/getAdminDrawList")
    public CommonJson getAdminDrawList() {
        logger.info(">>>>>>>>>>>>>>>>>>AdminWechatOrderController.getWechatOrderList>>>>>>>>>>>>");

        List<AdminDrawModel> adminDrawModelList = Lists.newArrayList(adminDrawResponsitory.findAll());

        CommonJson json = new CommonJson();
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", adminDrawModelList);
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    @PostMapping(value = "/saveAdminDraw")
    public CommonJson saveAdminDraw() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AdminDrawController.saveAdminDraw>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String id = jsonObject.getString("id");
        String scale = jsonObject.getString("scale");

        AdminDrawModel adminDrawModel = adminDrawResponsitory.getByIdIs(id);
        adminDrawModel.setScale(scale);
        adminDrawModel = adminDrawResponsitory.save(adminDrawModel);

        CommonJson json = new CommonJson();
        Map<String, Object> map = Maps.newHashMap();
        map.put("info", adminDrawModel);
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }
}
