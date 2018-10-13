package com.springboot.fuhui.wechat.onlineCourses.repository;

import com.springboot.fuhui.wechat.onlineCourses.model.OnlineCourseModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/13
 * Time:16:01
 **/
public interface OnlineCourseRepository extends PagingAndSortingRepository<OnlineCourseModel, Long>, JpaSpecificationExecutor<OnlineCourseModel> {
    void deleteById(String id);

    OnlineCourseModel getByIdIs(String id);
}
