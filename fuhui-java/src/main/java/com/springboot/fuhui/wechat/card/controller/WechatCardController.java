package com.springboot.fuhui.wechat.card.controller;

import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.StaffCacheUtil;
import com.springboot.fuhui.web.adminUser.model.AdminUserModel;
import com.springboot.fuhui.wechat.card.model.CardModel;
import com.springboot.fuhui.wechat.card.respository.CardRepository;
import com.springboot.fuhui.wechat.wechatUser.model.WechatUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/7
 * Time:18:57
 **/
@RestController
@RequestMapping(path = "/mp/wechatCard")
public class WechatCardController {

    private final Logger logger = LoggerFactory.getLogger(WechatCardController.class);

    @Autowired
    CardRepository cardRepository;

    /**
     * 获取卡券列表
     * @return
     */
    @PostMapping(value = "/getCardList")
    public CommonJson getCardList() {
        String token = ContextHolderUtils.getRequest().getHeader("token");

        logger.info("WechatCardController.getCardList>>>>>>>>>>>>token:" + token);

        WechatUserModel wechatUserModel = null;

        try {
            wechatUserModel = (WechatUserModel) StaffCacheUtil.create().get(token, new Callable<AdminUserModel>() {
                @Override
                public AdminUserModel call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<CardModel> cardModelList = cardRepository.findAllByOpenIdOrderByCreateDateDesc(wechatUserModel.getOpenId());

        Map<String, Object> map = Maps.newHashMap();
        map.put("list", cardModelList);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);

        return json;
    }

}
