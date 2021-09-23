package com.cctvcc.auth.service;

import cn.cctvcc.core.domain.R;
import com.cctvcc.auth.domain.LoginBody;

/**
 * @description: 用户登录校验
 * @author: Jiang
 * @create: 2021-09-23 17:01
 */
public interface LoginService {

    /**
     * 登录
     */
    R<?> login(LoginBody loginBody);

}
