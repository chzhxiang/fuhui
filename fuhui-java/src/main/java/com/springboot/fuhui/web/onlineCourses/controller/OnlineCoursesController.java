package com.springboot.fuhui.web.onlineCourses.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.wechat.onlineCourses.model.OnlineCourseModel;
import com.springboot.fuhui.wechat.onlineCourses.repository.OnlineCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/13
 * Time:16:06
 **/
@RestController
@RequestMapping(path = "/admin/onlineCourses")
public class OnlineCoursesController {
    private final Logger logger = LoggerFactory.getLogger(OnlineCoursesController.class);

    @Autowired
    OnlineCourseRepository onlineCourseRepository;

    /**
     * 保存线上课程
     * @return
     */
    @PostMapping(value = "/saveOnlineCourse")
    public CommonJson saveOnlineCourse() throws IOException, ParseException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("OnlineCoursesController.saveOnlineCourse>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");

        OnlineCourseModel onlineCourseModel;

        // 如果id为空，则为保存
        if (Strings.isNullOrEmpty(id)) {
            onlineCourseModel = new OnlineCourseModel();
            onlineCourseModel.setCreateDate(new Date());
        } else {
            onlineCourseModel = onlineCourseRepository.getByIdIs(id);
            onlineCourseModel.setUpdateDate(new Date());
        }

        String address = jsonObject.getString("address");
        String description = jsonObject.getString("description");
        String name = jsonObject.getString("name");
        String productId = jsonObject.getString("productId");
        String startDate = jsonObject.getString("startDate");
        String endDate = jsonObject.getString("endDate");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        onlineCourseModel.setAddress(address);
        onlineCourseModel.setDescription(description);
        onlineCourseModel.setName(name);
        onlineCourseModel.setProductId(productId);
        onlineCourseModel.setStartDate(sdf.parse(startDate));
        onlineCourseModel.setEndDate(sdf.parse(endDate));

        onlineCourseModel = onlineCourseRepository.save(onlineCourseModel);

        Map<String, Object> map = Maps.newHashMap();
        map.put("info", onlineCourseModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;

    }

    /**
     * 获取线上课程列表
     * @return
     */
    @PostMapping(value = "/getOnlineCoursesList")
    public CommonJson getOnlineCoursesList() {
        logger.info(">>>>>>>>>>>>>>>>>>>OnlineCoursesController.getOnlineCoursesList>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<OnlineCourseModel> list = Lists.newArrayList(onlineCourseRepository.findAll(new Sort(Sort.Direction.DESC, "createDate")));
        CommonJson json = new CommonJson();
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", list);
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    /**
     * 根据id获取课程实体
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/getOnlineCourseById")
    public CommonJson getOnlineCourseById() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("OnlineCoursesController.delOnlineCoursesById>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");

        OnlineCourseModel onlineCourseModel = onlineCourseRepository.getByIdIs(id);

        Map<String, Object> map = Maps.newHashMap();
        map.put("info", onlineCourseModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");
        return json;
    }

    /**
     * 根据id删除线上课程
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delOnlineCoursesById")
    @Transactional
    public CommonJson delOnlineCoursesById() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("OnlineCoursesController.delOnlineCoursesById>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");

        onlineCourseRepository.deleteById(id);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        return json;
    }
}
