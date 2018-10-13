package com.springboot.fuhui.web.product.controller;

import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.wechat.product.model.ProductModel;
import com.springboot.fuhui.wechat.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/13
 * Time:18:28
 **/
@RestController
@RequestMapping(path = "/admin/adminProduct")
public class AdminProductController {
    private final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

    @Autowired
    ProductRepository productRepository;

    /**
     * 查询所有课程相关产品
     * @return
     */
    @PostMapping(value = "/getCourseList")
    public CommonJson getCourseList() {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>AdminProductController.getCourseList>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<ProductModel> productModelList = productRepository.findByTypeGreaterThan("1");
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", productModelList);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }
}
