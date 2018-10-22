package com.springboot.fuhui.wechat.onlineCourses.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.wechat.onlineCourses.model.OnlineCourseModel;
import com.springboot.fuhui.wechat.onlineCourses.repository.OnlineCourseRepository;
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
 * Date:2018/10/20
 * Time:15:23
 **/
@RestController
@RequestMapping(path = "/mp/onlineCourse")
public class OnlineCourseController {

    private final Logger logger = LoggerFactory.getLogger(OnlineCourseController.class);

    @Autowired
    OnlineCourseRepository onlineCourseRepository;

    /**
     * 根据cardType查询课程列表
     * @return
     */
    @PostMapping(value = "/getCourseListByCardType")
    public CommonJson getCourseListByCardType() throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("OnlineCourseController.getCourseListByCardType>>>>>>>>>>>>token:" + token + ",params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String cardType = jsonObject.getString("cardType");

        List<OnlineCourseModel> list = onlineCourseRepository.findAllByProductIdAndFlag(cardType, "1");

        Map<String, Object> map = Maps.newHashMap();
        map.put("list", list);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

}
