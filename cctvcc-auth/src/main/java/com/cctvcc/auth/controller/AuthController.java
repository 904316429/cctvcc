package com.cctvcc.auth.controller;

import cn.cctvcc.core.domain.R;
import com.cctvcc.auth.domain.LoginBody;
import com.cctvcc.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: token 控制类
 * @author: Jiang
 * @create: 2021-09-23 14:38
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录
     */
    @PostMapping( value = "login")
    public R<?> lgoin(LoginBody loginBody){
        return authService.login(loginBody.getUsername(), loginBody.getPassword());
    }

    /**
     * 登出
     */
    @PostMapping( value = "logout")
    public R<?> logout(HttpServletRequest request){
        return authService.logout(request);
    }



}
