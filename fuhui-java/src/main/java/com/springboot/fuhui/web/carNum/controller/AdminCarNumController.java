package com.springboot.fuhui.web.carNum.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.wechat.carNum.model.CarNumModel;
import com.springboot.fuhui.wechat.carNum.repository.CarNumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/13
 * Time:11:25
 **/
@RestController
@RequestMapping(path = "/admin/adminCarNum")
public class AdminCarNumController {
    private final Logger logger = LoggerFactory.getLogger(AdminCarNumController.class);

    @Autowired
    CarNumRepository carNumRepository;

    /**
     * 根据手机号查询绑定车牌
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/getCarNumListByPhone")
    public CommonJson getCarNumListByPhone() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AdminCarNumController.getCarNumListByPhone>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String phone = jsonObject.getString("phone");

        List<CarNumModel> carNumModelList = carNumRepository.findAllByPhoneOrderByCreateDateDesc(phone);

        CommonJson json = new CommonJson();
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", carNumModelList);
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    /**
     * 解绑车牌信息
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delCarNum")
    @Transactional
    public CommonJson delCarNum() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AdminCarNumController.delCarNum>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");
        carNumRepository.deleteById(id);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        return json;
    }
}
