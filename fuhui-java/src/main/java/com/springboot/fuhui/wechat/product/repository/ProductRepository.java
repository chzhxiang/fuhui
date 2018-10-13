package com.springboot.fuhui.wechat.product.repository;

import com.springboot.fuhui.wechat.product.model.ProductModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/7
 * Time:20:14
 **/
public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {
    ProductModel getByIdIs(String id);

    List<ProductModel> findByTypeGreaterThan(String type);
}
