package cn.cctvcc.system.service;

import cn.cctvcc.system.api.domain.SysUser;


/**
 * @description: 用户信息接口类
 * @author: Jiang
 * @create: 2021-09-22 15:59
 */
public interface SysUserService {

    /**
     * 根据用户名称获取用户
     *
     * @param userName
     * @return
     */
    SysUser selectUserByUserName(String userName);

}
