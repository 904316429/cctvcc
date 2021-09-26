package cn.cctvcc.system.service.impl;

import cn.cctvcc.system.api.domain.SysUser;
import cn.cctvcc.system.mapper.SysUserMapper;
import cn.cctvcc.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户信息实现类
 * @author: Jiang
 * @create: 2021-09-22 15:59
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名称获取用户
     * @param userName
     * @return
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser :: getUserName, userName);
        return sysUserMapper.selectOne(queryWrapper);
    }

}
