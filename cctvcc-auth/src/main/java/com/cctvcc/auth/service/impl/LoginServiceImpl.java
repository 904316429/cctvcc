package com.cctvcc.auth.service.impl;

import cn.cctvcc.core.constant.UserConstants;
import cn.cctvcc.core.domain.R;
import cn.cctvcc.core.enums.common.CommonEnum;
import cn.cctvcc.core.utils.SecurityUtils;
import cn.cctvcc.system.api.RemoteSysUserService;
import cn.cctvcc.system.api.domain.LoginUser;
import cn.cctvcc.system.api.domain.SysUser;
import com.cctvcc.auth.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description: 用户登录校验
 * @author: Jiang
 * @create: 2021-09-23 17:02
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RemoteSysUserService remoteSysUserService;

    /**
     * 登录
     */
    @Override
    public R<?> login(String username, String password) {

        // 用户名或密码为空
        if (StringUtils.isAnyBlank(username, password))
            throw new RuntimeException("用户/密码必须填写");
        // 密码如果不在指定范围内
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
            throw new RuntimeException("用户密码不在指定范围");
        // 用户名不在指定范围内
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH || username.length() > UserConstants.USERNAME_MAX_LENGTH)
            throw new RuntimeException("用户名不在指定范围");

        // 获取用户信息
        R<LoginUser> userResult = remoteSysUserService.userInfo(username);
        if (Objects.equals(R.FAIL, userResult.getCode()))
            throw new RuntimeException(userResult.getMsg());
        if (Objects.isNull( userResult) || Objects.isNull( userResult.getData()))
            throw new RuntimeException("用户或密码错误");

        LoginUser loginUser = userResult.getData();
        SysUser sysUser = loginUser.getSysUser();
        if(Objects.equals(CommonEnum.DELETED.getCode(), sysUser.getDelFlag()))
            throw new RuntimeException("对不起，您的账号"+ username + " 已被删除");
        if(Objects.equals(CommonEnum.DISABLE.getCode(), sysUser.getDelFlag()))
            throw new RuntimeException("对不起，您的账号"+ username + " 已被停用");
        if(!SecurityUtils.matchesPassword(password, sysUser.getPassword()))
            throw new RuntimeException("用户或密码错误");

        return R.ok(loginUser);
    }
}
