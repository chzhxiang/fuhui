package com.springboot.fuhui.web.adminUser.repository;

import com.springboot.fuhui.web.adminUser.model.AdminUserModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @program: fuhui
 * @description:
 * @author: weishiyao
 * @create: 2018-10-03 09:14
 **/
public interface AdminUserRespository extends PagingAndSortingRepository<AdminUserModel, Long>, JpaSpecificationExecutor<AdminUserModel> {
    AdminUserModel getByUserNameAndPassword(String userName, String password);
}
