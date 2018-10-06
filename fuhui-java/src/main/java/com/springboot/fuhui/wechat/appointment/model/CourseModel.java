package com.springboot.fuhui.wechat.appointment.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: fuhui
 * @description: 课程表
 * @author: weishiyao
 * @create: 2018-10-02 17:38
 **/
@Entity
@Table(name = "cours")
public class CourseModel {
    private String id;
    // 名称
    private String name;
    // 描述
    private String description;
    // 地点
    private String address;
    // 创建时间
    private Date createDate;
    // 上课时间
    private Date coursDate;
    // 课程类型
    private String type;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID",nullable=false,length=36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCoursDate() {
        return coursDate;
    }

    public void setCoursDate(Date coursDate) {
        this.coursDate = coursDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
