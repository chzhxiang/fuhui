package com.springboot.fuhui.web.adminUser.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.springboot.fuhui.system.model.CommonJson;
import com.springboot.fuhui.system.utils.ContextHolderUtils;
import com.springboot.fuhui.system.utils.HttpUtils;
import com.springboot.fuhui.system.utils.StaffCacheUtil;
import com.springboot.fuhui.web.adminUser.model.AdminUserModel;
import com.springboot.fuhui.web.adminUser.repository.AdminUserRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @program: fuhui
 * @description:
 * @author: weishiyao
 * @create: 2018-09-29 20:17
 **/
@RestController
@RequestMapping(path = "/admin/adminUser")
public class AdminUserController {

    private final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    AdminUserRespository adminUserRespository;

    /**
     * 校验用户名是否存在
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/checkUserName")
    public CommonJson checkUserName() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AdminUserController.login>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);
        String userName = jsonObject.getString("username");
        CommonJson json = new CommonJson();
        json.setResultCode("0");
        json.setResultMsg("success");
        return json;
    }

    /**
     * 管理员登录
     * @return
     * @throws IOException
     * @throws ExecutionException
     */
    @PostMapping(value = "/login")
    public CommonJson login() throws IOException, ExecutionException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());

        logger.info("AdminUserController.login>>>>>>>>>>>>params:" + params);

        JSONObject jsonObject = JSON.parseObject(params);

        String userName = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        CommonJson json = new CommonJson();

        // ----------校验用户名密码是否正确---------------
        AdminUserModel adminUserModel = adminUserRespository.getByUserNameAndPassword(userName, password);

        if (adminUserModel != null) {
            // ----------用户校验通过，生成token存入缓存----------
            String token = UUID.randomUUID().toString();
            logger.info("AdminUserController.login>>>>>>>>>>>>token:" + token);
            StaffCacheUtil.create().put(token, adminUserModel);


            Map<String, Object> map = Maps.newHashMap();
            map.put("token", token);
            json.setResultCode("1");
            json.setResultData(map);
            json.setResultMsg("success");
        } else {
            json.setResultCode("0");
            json.setResultMsg("用户名密码有误，请核对后再输入");
        }


        return json;
    }

    /**
     * 获取管理员用户信息
     * @param token
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/info")
    public CommonJson info(@RequestParam String token) {
        AdminUserModel adminUserModel = null;

        // ------缓存中获取用户信息--------
        try {
            adminUserModel = (AdminUserModel) StaffCacheUtil.create().get(token, new Callable<AdminUserModel>() {
                @Override
                public AdminUserModel call() {
                    return new AdminUserModel();
                }
            });
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        }

        CommonJson json = new CommonJson();

        // -------缓存中不存在用户信息-------
        if (Strings.isNullOrEmpty(adminUserModel.getId())) {
            json.setResultCode("-1");
            json.setResultMsg("user is no login");
        } else {
            json.setResultCode("1");
            Map<String, Object> map = Maps.newHashMap();
            map.put("adminUserModel", adminUserModel);
            json.setResultData(map);
            json.setResultMsg("success");
        }
        return json;
    }

    /**
     * 管理员登出
     * @return
     * @throws ExecutionException
     */
    @PostMapping(value = "/logout")
    public CommonJson logout() {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        logger.info("AdminUserController.logout>>>>>>>>>>>>token:" + token);

        StaffCacheUtil.create().remove(token);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        return json;

    }
}
