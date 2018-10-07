package com.springboot.fuhui.wechat.wechatUser.repository;

import com.springboot.fuhui.wechat.wechatUser.model.WechatUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @program: fuhui
 * @description:
 * @author: weishiyao
 * @create: 2018-10-03 10:11
 **/
public interface WechatUserRespository extends PagingAndSortingRepository<WechatUserModel, Long>, JpaSpecificationExecutor<WechatUserModel> {
    WechatUserModel getByIdIs(String id);

    Page<WechatUserModel> getByPhoneIs(String phone, Pageable pageable);

    WechatUserModel getByOpenIdIs(String openId);
}
