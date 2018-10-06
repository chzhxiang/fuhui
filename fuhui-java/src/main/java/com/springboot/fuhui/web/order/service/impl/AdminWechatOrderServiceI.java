package com.springboot.fuhui.web.order.service.impl;

import com.google.common.base.Strings;
import com.springboot.fuhui.web.order.service.AdminWechatOrderService;
import com.springboot.fuhui.wechat.order.model.WechatOrderModel;
import com.springboot.fuhui.wechat.order.model.WechatOrderModel_;
import com.springboot.fuhui.wechat.order.responsitory.WechatOrderResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * Created with IDEA
 * author: inwsy@hotmail.com
 * Date:2018/10/3
 * Time:17:28
 **/
@Service
@Transactional
public class AdminWechatOrderServiceI implements AdminWechatOrderService {

    @Autowired
    WechatOrderResponsitory wechatOrderResponsitory;

    @Override
    public Page<WechatOrderModel> getWechatOrderList(final String phone, final String payType, final String orderStatus, final String orderNo, final String transactionId, final String txOrderNo, Pageable pageable) {

        return wechatOrderResponsitory.findAll(new Specification<WechatOrderModel>() {
            @Override
            public Predicate toPredicate(Root<WechatOrderModel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = null;

                if (!Strings.isNullOrEmpty(phone)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(WechatOrderModel_.phone), phone);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (!Strings.isNullOrEmpty(payType)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(WechatOrderModel_.payType), payType);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (!Strings.isNullOrEmpty(orderStatus)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(WechatOrderModel_.orderStatus), orderStatus);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (!Strings.isNullOrEmpty(orderNo)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(WechatOrderModel_.orderNo), orderNo);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (!Strings.isNullOrEmpty(transactionId)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(WechatOrderModel_.transactionId), transactionId);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (!Strings.isNullOrEmpty(txOrderNo)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(WechatOrderModel_.txOrderNo), txOrderNo);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (p1 != null) {
                    criteriaQuery.where(criteriaBuilder.and(p1));
                }

                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("payTime").as(Date.class)));

                return criteriaQuery.getRestriction();
            }
        }, pageable);
    }
}
