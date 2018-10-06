package com.springboot.fuhui.web.draw.responsitory;

import com.springboot.fuhui.web.draw.model.AdminDrawModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/3
 * Time:18:54
 **/
public interface AdminDrawResponsitory extends PagingAndSortingRepository<AdminDrawModel, Long>, JpaSpecificationExecutor<AdminDrawModel> {

    AdminDrawModel getByIdIs(String id);
}
