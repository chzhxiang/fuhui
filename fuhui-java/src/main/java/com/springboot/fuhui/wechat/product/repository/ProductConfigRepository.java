package com.springboot.fuhui.wechat.product.repository;

import com.springboot.fuhui.wechat.product.model.ProductConfigModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/16
 * Time:23:39
 **/
public interface ProductConfigRepository extends PagingAndSortingRepository<ProductConfigModel, Long>, JpaSpecificationExecutor<ProductConfigModel> {
    List<ProductConfigModel> findAllByProductId(String productId);
}
