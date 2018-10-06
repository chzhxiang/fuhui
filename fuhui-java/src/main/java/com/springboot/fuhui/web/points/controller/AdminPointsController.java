package com.springboot.fuhui.web.points.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.wechat.points.model.PointsLogModel;
import com.springboot.fuhui.wechat.points.repository.PointsLogResponsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @program: fuhui
 * @description:
 * @author: weishiyao
 * @create: 2018-10-03 14:21
 **/
@RestController
@RequestMapping(path = "/admin/adminPoints")
public class AdminPointsController {
    private final Logger logger = LoggerFactory.getLogger(AdminPointsController.class);

    @Autowired
    PointsLogResponsitory pointsLogResponsitory;

    @PostMapping(value = "/getPointsList")
    public CommonJson getPointsList() throws IOException {

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AdminPointsController.getPointsList>>>>>>>>>>>>params:" + params);

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

        Page<PointsLogModel> page = null;

        if (Strings.isNullOrEmpty(phone)) {
            page = pointsLogResponsitory.findByOrderByCreateDateDesc(new PageRequest(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
        } else {
            page = pointsLogResponsitory.findAllByPhoneOrderByCreateDateDesc(phone, new PageRequest(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));
        }

        CommonJson json = new CommonJson();

        Map<String, Object> map = Maps.newHashMap();
        map.put("page", page);

        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }
}
