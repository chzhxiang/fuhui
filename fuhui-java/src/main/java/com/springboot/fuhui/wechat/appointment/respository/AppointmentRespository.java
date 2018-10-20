package com.springboot.fuhui.wechat.appointment.respository;

import com.springboot.fuhui.wechat.appointment.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/20
 * Time:10:10
 **/
public interface AppointmentRespository extends PagingAndSortingRepository<AppointmentModel, Long>, JpaSpecificationExecutor<AppointmentModel> {
    List<AppointmentModel> findAllByAppointmentDateAndAppointmentPeroidAndFlag(String appointmentDate, String appointmentPeroid, String flag);

    List<AppointmentModel> findAllByOpenIdAndAppointmentDateAndAppointmentPeroidAndFlag(String openId, String appointmentDate, String appointmentPeroid, String flag);

    List<AppointmentModel> findAllByCourseIdAndFlag(String coursId, String flag);

    List<AppointmentModel> findAllByOpenIdAndCourseIdAndFlag(String openid, String courseId, String flag);
}
