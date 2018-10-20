package com.springboot.fuhui.wechat.card.respository;

import com.springboot.fuhui.wechat.card.model.CardModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/7
 * Time:19:00
 **/
public interface CardRepository extends PagingAndSortingRepository<CardModel, Long>, JpaSpecificationExecutor<CardModel> {
    List<CardModel> findAllByOpenIdOrderByCreateDateDesc(String openId);

    CardModel getByIdIs(String id);
}
