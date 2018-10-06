package com.springboot.fuhui.web.adminUser.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @program: fuhui
 * @description: 管理员表
 * @author: weishiyao
 * @create: 2018-09-30 23:51
 **/
@Entity
@Table(name = "admin_user")
public class AdminUserModel {

    private String id;

    private String userName;

    private String password;

    private String roles;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
