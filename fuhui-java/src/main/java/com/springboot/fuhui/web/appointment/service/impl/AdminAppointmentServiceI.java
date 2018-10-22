package com.springboot.fuhui.web.appointment.service.impl;

import com.google.common.base.Strings;
import com.springboot.fuhui.web.appointment.service.AdminAppointmentService;
import com.springboot.fuhui.wechat.appointment.model.AppointmentModel;
import com.springboot.fuhui.wechat.appointment.model.AppointmentModel_;
import com.springboot.fuhui.wechat.appointment.respository.AppointmentRespository;
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
 * author: weishiyao
 * Date:2018/10/22
 * Time:22:30
 **/
@Service
@Transactional
public class AdminAppointmentServiceI implements AdminAppointmentService {

    @Autowired
    AppointmentRespository appointmentRespository;

    @Override
    public Page<AppointmentModel> getAppointmentList(final String phone, final String flag, final String status, Pageable pageable) {
        return appointmentRespository.findAll(new Specification<AppointmentModel>() {
            @Override
            public Predicate toPredicate(Root<AppointmentModel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = null;

                if (!Strings.isNullOrEmpty(phone)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(AppointmentModel_.phone), phone);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (!Strings.isNullOrEmpty(flag)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(AppointmentModel_.flag), flag);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (!Strings.isNullOrEmpty(status)) {
                    Predicate p2 = criteriaBuilder.equal(root.get(AppointmentModel_.status), status);
                    if (p1 != null) {
                        p1 = criteriaBuilder.and(p1, p2);
                    } else {
                        p1 = p2;
                    }
                }

                if (p1 != null) {
                    criteriaQuery.where(criteriaBuilder.and(p1));
                }

                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate").as(Date.class)));


                return null;
            }
        }, pageable);
    }
}
