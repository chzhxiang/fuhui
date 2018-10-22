package com.springboot.fuhui.wechat.appointment.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/22
 * Time:21:41
 **/
@StaticMetamodel(AppointmentModel.class)
public class AppointmentModel_ {

    public static volatile SingularAttribute<AppointmentModel, String> id;
    public static volatile SingularAttribute<AppointmentModel, Date> createDate;
    public static volatile SingularAttribute<AppointmentModel, Date> updateDate;
    public static volatile SingularAttribute<AppointmentModel, String> openId;
    public static volatile SingularAttribute<AppointmentModel, String> phone;
    public static volatile SingularAttribute<AppointmentModel, String> cardId;
    public static volatile SingularAttribute<AppointmentModel, String> name;
    public static volatile SingularAttribute<AppointmentModel, String> description;
    // 篮球场预约日期
    public static volatile SingularAttribute<AppointmentModel, String> appointmentDate;
    // 篮球场预约时间段
    public static volatile SingularAttribute<AppointmentModel, String> appointmentPeroid;
    // 状态 0未使用 1已使用
    public static volatile SingularAttribute<AppointmentModel, String> status;
    // 产品类型 0.停车场 1.篮球场 2.线上课程A 3.线上课程B
    public static volatile SingularAttribute<AppointmentModel, String> type;
    // 课程id
    public static volatile SingularAttribute<AppointmentModel, String> courseId;
    public static volatile SingularAttribute<AppointmentModel, Date> courseStartDate;
    public static volatile SingularAttribute<AppointmentModel, Date> courseEndDate;
    // 是否有效 0无效 1有效
    public static volatile SingularAttribute<AppointmentModel, String> flag;
}
