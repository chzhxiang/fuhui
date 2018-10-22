package com.springboot.fuhui.web.appointment.service;

import com.springboot.fuhui.wechat.appointment.model.AppointmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/22
 * Time:22:29
 **/
public interface AdminAppointmentService {
    Page<AppointmentModel> getAppointmentList(String phone, String flag, String status, Pageable pageable);
}
