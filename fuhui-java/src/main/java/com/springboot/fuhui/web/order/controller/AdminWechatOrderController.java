package com.springboot.fuhui.web.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.web.order.service.AdminWechatOrderService;
import com.springboot.fuhui.wechat.order.model.WechatOrderModel;
import com.springboot.fuhui.wechat.order.responsitory.WechatOrderResponsitory;
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
 * Created with IDEA
 * author: inwsy@hotmail.com
 * Date:2018/10/3
 * Time:17:25
 **/
@RestController
@RequestMapping(path = "/admin/adminWechatOrder")
public class AdminWechatOrderController {
    private final Logger logger = LoggerFactory.getLogger(AdminWechatOrderController.class);

    @Autowired
    AdminWechatOrderService adminWechatOrderService;

    @PostMapping(value = "/getWechatOrderList")
    public CommonJson getWechatOrderList() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AdminWechatOrderController.getWechatOrderList>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String phone = jsonObject.getString("phone");
        // 支付类型
        String payType = jsonObject.getString("payType");
        // 订单状态
        String orderStatus = jsonObject.getString("orderStatus");
        String orderNo = jsonObject.getString("orderNo");
        String transactionId = jsonObject.getString("transactionId");
        String txOrderNo = jsonObject.getString("txOrderNo");
        String pageNum = jsonObject.getString("pageNum");
        String pageSize = jsonObject.getString("pageSize");

        if (Strings.isNullOrEmpty(pageNum)) {
            pageNum = "1";
        }

        if (Strings.isNullOrEmpty(pageSize)) {
            pageSize = "10";
        }

        Page<WechatOrderModel> page = adminWechatOrderService.getWechatOrderList(phone, payType, orderStatus, orderNo, transactionId, txOrderNo, new PageRequest(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));

        CommonJson json = new CommonJson();

        Map<String, Object> map = Maps.newHashMap();
        map.put("page", page);

        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }


}
