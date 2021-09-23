package com.cctvcc.auth.controller;

import cn.cctvcc.core.domain.R;
import com.cctvcc.auth.domain.LoginBody;
import com.cctvcc.auth.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: token 控制类
 * @author: Jiang
 * @create: 2021-09-23 14:38
 */
@RestController
public class TokenController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping( value = "login")
    public R<?> lgoin(@RequestBody LoginBody loginBody){
        return this.loginService.login(loginBody);
    }

}
