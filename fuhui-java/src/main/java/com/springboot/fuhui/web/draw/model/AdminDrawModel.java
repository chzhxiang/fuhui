package com.springboot.fuhui.web.draw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/3
 * Time:18:54
 **/
@Entity
@Table(name = "admin_draw")
public class AdminDrawModel {
    private String id;
    private String name;
    private String scale;

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

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
