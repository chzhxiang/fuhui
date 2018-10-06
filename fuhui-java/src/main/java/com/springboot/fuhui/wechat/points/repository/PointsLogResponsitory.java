package com.springboot.fuhui.wechat.points.repository;

import com.springboot.fuhui.wechat.points.model.PointsLogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @program: fuhui
 * @description:
 * @author: weishiyao
 * @create: 2018-10-03 11:02
 **/
public interface PointsLogResponsitory extends PagingAndSortingRepository<PointsLogModel, Long>, JpaSpecificationExecutor<PointsLogModel> {
    Page<PointsLogModel> findByOrderByCreateDateDesc(Pageable pageable);

    Page<PointsLogModel> findAllByPhoneOrderByCreateDateDesc(String phone, Pageable pageable);
}
