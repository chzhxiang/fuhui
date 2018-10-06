package com.springboot.fuhui.wechat.order.responsitory;

import com.springboot.fuhui.wechat.order.model.WechatOrderModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IDEA
 * author: inwsy@hotmail.com
 * Date:2018/10/3
 * Time:17:26
 **/
public interface WechatOrderResponsitory extends PagingAndSortingRepository<WechatOrderModel, Long>, JpaSpecificationExecutor<WechatOrderModel> {
}
