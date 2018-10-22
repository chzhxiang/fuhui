package com.springboot.fuhui.web.appointment.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.web.appointment.service.AdminAppointmentService;
import com.springboot.fuhui.wechat.appointment.model.AppointmentModel;
import com.springboot.fuhui.wechat.appointment.respository.AppointmentRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/22
 * Time:21:31
 **/
@RestController
@RequestMapping(path = "/admin/adminAppointment")
public class AdminAppointmentController {
    private final Logger logger = LoggerFactory.getLogger(AdminAppointmentController.class);

    @Autowired
    AppointmentRespository appointmentRespository;

    @Autowired
    AdminAppointmentService adminAppointmentService;

    /**
     * 获取分页预约列表
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/getAppointmentList")
    public CommonJson getAppointmentList() throws IOException {

        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AppointmentController.getAppointmentList>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String phone = jsonObject.getString("phone");
        String flag = jsonObject.getString("flag");
        String status = jsonObject.getString("status");
        String pageNum = jsonObject.getString("pageNum");
        String pageSize = jsonObject.getString("pageSize");

        if (Strings.isNullOrEmpty(pageNum)) {
            pageNum = "1";
        }

        if (Strings.isNullOrEmpty(pageSize)) {
            pageSize = "10";
        }

        Page<AppointmentModel> appointmentModelPage = adminAppointmentService.getAppointmentList(phone, flag, status, new PageRequest(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));

        Map<String, Object> map = Maps.newHashMap();
        map.put("page", appointmentModelPage);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);
        return json;
    }

    /**
     * 取消预约
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/cancelAppointment")
    public CommonJson cancelAppointment() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AppointmentController.cancelAppointment>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String id = jsonObject.getString("id");

        AppointmentModel appointmentModel = appointmentRespository.getByIdIs(id);

        appointmentModel.setFlag("0");
        appointmentModel.setUpdateDate(new Date());
        appointmentRespository.save(appointmentModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(null);
        json.setResultMsg("success");
        return json;
    }


}
