package com.cctvcc.auth.controller;

import cn.cctvcc.core.domain.R;
import com.cctvcc.auth.domain.LoginBody;
import com.cctvcc.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: token 控制类
 * @author: Jiang
 * @create: 2021-09-23 14:38
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    public R<?> lgoin(LoginBody loginBody) throws Exception {
        return authService.login(loginBody.getUsername(), loginBody.getPassword());
    }

    /**
     * 登出
     */
    @PostMapping(value = "/logout")
    public R<?> logout(HttpServletRequest request) {
        return authService.logout(request);
    }

    /**
     * 获取公钥
     */
    @GetMapping(value = "/getPublicKey/{username}")
    public R<?> getPublicKey(@PathVariable("username") String username) throws Exception {
        return authService.getPublicKey(username);
    }


}
