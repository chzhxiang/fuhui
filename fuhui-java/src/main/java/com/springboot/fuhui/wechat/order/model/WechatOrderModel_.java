package com.springboot.fuhui.wechat.order.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created with IDEA
 * author: inwsy@hotmail.com
 * Date:2018/10/3
 * Time:17:56
 **/
@StaticMetamodel(WechatOrderModel.class)
public class WechatOrderModel_ {
    public static volatile SingularAttribute<WechatOrderModel, String> id;

    public static volatile SingularAttribute<WechatOrderModel, String> orderNo;//订单编号

    public static volatile SingularAttribute<WechatOrderModel, Date> orderTime;//订单创建时间

    public static volatile SingularAttribute<WechatOrderModel, Integer> orderMoney;//订单金额

    public static volatile SingularAttribute<WechatOrderModel, String> orderComment;//订单描述

    public static volatile SingularAttribute<WechatOrderModel, String> orderStatus;//支付状态 0:创建 1：订单完成

    public static volatile SingularAttribute<WechatOrderModel, String> openId;//支付openId

    public static volatile SingularAttribute<WechatOrderModel, Date> payTime;//发起支付时间

    public static volatile SingularAttribute<WechatOrderModel, String> txOrderNo;//腾讯订单ID

    public static volatile SingularAttribute<WechatOrderModel, String> paySuccessTime;//支付完成时间

    public static volatile SingularAttribute<WechatOrderModel, String> transactionId;//微信支付订单号

    public static volatile SingularAttribute<WechatOrderModel, Integer> payTimes;//第几次下单

    public static volatile SingularAttribute<WechatOrderModel, String> productNo; //商品编号

    public static volatile SingularAttribute<WechatOrderModel, Date> updateDate;

    public static volatile SingularAttribute<WechatOrderModel, String> payType; // 支付类型 0：微信支付 1：积分支付

    public static volatile SingularAttribute<WechatOrderModel, String> phone;
}
