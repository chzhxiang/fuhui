package com.springboot.fuhui.wechat.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.wechat.product.model.ProductConfigModel;
import com.springboot.fuhui.wechat.product.model.ProductModel;
import com.springboot.fuhui.wechat.product.repository.ProductConfigRepository;
import com.springboot.fuhui.wechat.product.repository.ProductRepository;
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
 * Date:2018/10/7
 * Time:20:15
 **/
@RestController
@RequestMapping(path = "/mp/wechatProduct")
public class WechatProductController {

    private final Logger logger = LoggerFactory.getLogger(WechatProductController.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductConfigRepository productConfigRepository;

    /**
     * 获取产品列表
     * @return
     */
    @PostMapping(value = "/getProductList")
    public CommonJson getProductList() {
        logger.info(">>>>>>>>>>>>>>>>>>WechatProductController.getProductList>>>>>>>>>>>>");
        List<ProductModel> productModelList = Lists.newArrayList(productRepository.findAll());
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", productModelList);
        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    /**
     * 根据id获取产品详情
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/getProductById")
    public CommonJson getProductById() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatProductController.getProductById>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");
        ProductModel productModel = productRepository.getByIdIs(id);

        Map<String, Object> map = Maps.newHashMap();
        map.put("info", productModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }

    /**
     * 根据产品id获取产品配置
     * @return
     */
    @PostMapping(value = "/getProductConfigByProductId")
    public CommonJson getProductConfigByProductId() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("WechatProductController.getProductConfigByProductId>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String productId = jsonObject.getString("productId");
        List<ProductConfigModel> list = productConfigRepository.findAllByProductId(productId);

        Map<String, Object> map = Maps.newHashMap();
        map.put("list", list);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

}
