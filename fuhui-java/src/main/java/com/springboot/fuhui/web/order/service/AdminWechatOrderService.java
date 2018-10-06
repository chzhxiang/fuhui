package com.springboot.fuhui.web.order.service;

import com.springboot.fuhui.wechat.order.model.WechatOrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author: inwsy@hotmail.com
 * Date:2018/10/3
 * Time:17:28
 **/
public interface AdminWechatOrderService {
    Page<WechatOrderModel> getWechatOrderList(String phone, String payType, String orderStatus, String orderNo, String transactionId, String txOrderNo, Pageable pageable);
}
