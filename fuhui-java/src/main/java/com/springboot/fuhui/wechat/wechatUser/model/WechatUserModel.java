package com.springboot.fuhui.wechat.wechatUser.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @program: fuhui
 * @description: 微信用户信息
 * @author: weishiyao
 * @create: 2018-10-01 20:37
 **/
@Entity
@Table(name = "wechat_user")
public class WechatUserModel {

    private String id;
    private String openId;
    private String phone;
    private Date createDate;
    private Date updateDate;
    // 积分
    private Integer points;
    // 车牌号
    private String carNumber;

    private String nickname;
    private Integer sex;
    // 微信头像
    private String wechatHeadImg;
    private String unionId;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWechatHeadImg() {
        return wechatHeadImg;
    }

    public void setWechatHeadImg(String wechatHeadImg) {
        this.wechatHeadImg = wechatHeadImg;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
