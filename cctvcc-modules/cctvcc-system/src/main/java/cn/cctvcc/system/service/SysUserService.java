package cn.cctvcc.system.service;

import cn.cctvcc.system.domain.entity.SysUser;

import java.util.List;

/**
 * @description: 用户信息接口类
 * @author: Jiang
 * @create: 2021-09-22 15:59
 */
public interface SysUserService {

    /**
     * 用户信息列表查询
     * @param sysUser
     * @return
     */
    List<SysUser> selectList(SysUser sysUser);

}
