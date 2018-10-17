package com.springboot.fuhui.wechat.card.respository;

import com.springboot.fuhui.wechat.card.model.CardUseLogModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/18
 * Time:0:00
 **/
public interface CardUseLogReponsitory extends PagingAndSortingRepository<CardUseLogModel, Long>, JpaSpecificationExecutor<CardUseLogModel> {
    List<CardUseLogModel> findAllByOpenIdAndCardIdOrderByCreateDateDesc(String openId, String cardId);
}
