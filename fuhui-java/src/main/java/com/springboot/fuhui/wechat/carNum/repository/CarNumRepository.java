package com.springboot.fuhui.wechat.carNum.repository;

import com.springboot.fuhui.wechat.carNum.model.CarNumModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/13
 * Time:11:25
 **/
public interface CarNumRepository extends PagingAndSortingRepository<CarNumModel, Long>, JpaSpecificationExecutor<CarNumModel> {
    List<CarNumModel> findAllByPhoneOrderByCreateDateDesc(String phone);
    List<CarNumModel> findAllByCarNum(String carNum);
    List<CarNumModel> findAllByOpenid(String openId);
    CarNumModel getByIdIs(String id);
    void deleteById(String id);
}
