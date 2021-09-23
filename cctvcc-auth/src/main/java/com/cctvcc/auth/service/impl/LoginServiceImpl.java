package com.cctvcc.auth.service.impl;

import cn.cctvcc.core.domain.R;
import cn.cctvcc.system.api.domain.LoginUser;
import com.cctvcc.auth.domain.LoginBody;
import com.cctvcc.auth.service.LoginService;
import com.cctvcc.auth.service.RemoteSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户登录校验
 * @author: Jiang
 * @create: 2021-09-23 17:02
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RemoteSysUserService remoteSysUserService;

    /**
     * 登录
     */
    @Override
    public R<?> login(LoginBody loginBody) {
        R<LoginUser> userResult = remoteSysUserService.userInfo(loginBody.getUsername());
        System.out.println(userResult);
        return null;
    }
}
