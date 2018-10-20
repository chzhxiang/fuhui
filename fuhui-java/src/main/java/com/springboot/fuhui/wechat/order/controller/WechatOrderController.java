package com.springboot.fuhui.wechat.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.system.utils.StaffCacheUtil;
import com.springboot.fuhui.web.adminUser.model.AdminUserModel;
import com.springboot.fuhui.wechat.card.model.CardModel;
import com.springboot.fuhui.wechat.card.respository.CardRepository;
import com.springboot.fuhui.wechat.order.model.WechatOrderModel;
import com.springboot.fuhui.wechat.order.responsitory.WechatOrderResponsitory;
import com.springboot.fuhui.wechat.points.model.PointsLogModel;
import com.springboot.fuhui.wechat.points.repository.PointsLogResponsitory;
import com.springboot.fuhui.wechat.product.model.ProductModel;
import com.springboot.fuhui.wechat.product.repository.ProductRepository;
import com.springboot.fuhui.wechat.wechatUser.model.WechatUserModel;
import com.springboot.fuhui.wechat.wechatUser.repository.WechatUserRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/7
 * Time:23:02
 **/
@RestController
@RequestMapping(path = "/mp/wechatOrder")
public class WechatOrderController {

    private final Logger logger = LoggerFactory.getLogger(WechatOrderController.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WechatUserRespository wechatUserRespository;

    @Autowired
    PointsLogResponsitory pointsLogResponsitory;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    WechatOrderResponsitory wechatOrderResponsitory;

    /**
     * 提交订单
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/submitOrder")
    public CommonJson submitOrder() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatProductController.submitOrder>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String id = jsonObject.getString("id");
        String useNum = jsonObject.getString("useNum");
        String payType = jsonObject.getString("payType");

        ProductModel productModel = productRepository.getByIdIs(id);

        CommonJson json = new CommonJson();

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

        wechatUserModel = wechatUserRespository.getByOpenIdIs(wechatUserModel.getOpenId());



        // 如果当前支付类型是积分
        if ("0".equals(payType)) {

            int needPayPoints = new BigDecimal(productModel.getPointsPrice()).multiply(new BigDecimal(useNum)).intValue();

            if (wechatUserModel.getPoints() < needPayPoints) {
                json.setResultCode("0");
                json.setResultMsg("积分不足");
                return json;
            }

            // 先计算账户变动
            wechatUserModel.setPoints(wechatUserModel.getPoints() - needPayPoints);
            wechatUserModel.setUpdateDate(new Date());

            // 增加积分变动log
            PointsLogModel pointsLogModel = new PointsLogModel();
            pointsLogModel.setCreateDate(new Date());
            pointsLogModel.setOpenId(wechatUserModel.getOpenId());
            pointsLogModel.setPhone(wechatUserModel.getPhone());
            pointsLogModel.setPoints(needPayPoints);
            // 积分变动类型 0：服务台增加 1：用户扫描增加 2：消费
            pointsLogModel.setType("2");

            // 增加订单
            WechatOrderModel wechatOrderModel = new WechatOrderModel();
            wechatOrderModel.setPayTime(new Date());
            wechatOrderModel.setOpenId(wechatUserModel.getOpenId());
            wechatOrderModel.setPhone(wechatOrderModel.getPhone());
            wechatOrderModel.setOrderMoney(needPayPoints);
            wechatOrderModel.setOrderStatus("1");
            wechatOrderModel.setPayType(productModel.getType());
            wechatOrderModel.setProductNo(productModel.getId());
            wechatOrderModel.setProductName(productModel.getName());

            // 该账户下增加卡券
            CardModel cardModel = new CardModel();
            cardModel.setCreateDate(new Date());
            cardModel.setOpenId(wechatUserModel.getOpenId());
            cardModel.setPhone(wechatUserModel.getPhone());
            cardModel.setProductName(productModel.getName());
            // 卡片类型 0：停车场 1：在线课程 2：篮球场
            cardModel.setType(productModel.getType());
            // 状态 0：未使用 1：已使用
            cardModel.setStatus("0");
            cardModel.setUseNum(useNum);

            // 保存数据
            wechatUserRespository.save(wechatUserModel);
            wechatOrderResponsitory.save(wechatOrderModel);
            cardRepository.save(cardModel);
            pointsLogResponsitory.save(pointsLogModel);

            json.setResultCode("1");
            json.setResultMsg("支付成功");
            return json;
        } else {
            // 微信支付，暂不考虑
            return null;
        }
    }

    /**
     * 获取订单列表
     * @return
     */
    @PostMapping(value = "/getOrderList")
    public CommonJson getOrderList() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", Lists.newArrayList(wechatOrderResponsitory.findAll(new Sort(Sort.Direction.DESC, "payTime"))));
        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);
        return json;
    }
}
