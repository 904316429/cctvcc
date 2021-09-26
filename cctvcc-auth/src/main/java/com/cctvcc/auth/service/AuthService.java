package com.cctvcc.auth.service;

import cn.cctvcc.core.domain.R;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 用户登录校验
 * @author: Jiang
 * @create: 2021-09-23 17:01
 */
public interface AuthService {

    /**
     * 登录
     */
    R<?> login(String username, String password) throws Exception;

    /**
     * 登出
     */
    R<?> logout(HttpServletRequest request);

    /**
     * 获取公钥
     */
    R<?> getPublicKey(String username) throws Exception;

}
