package cn.cctvcc.system.service.impl;

import cn.cctvcc.system.domain.entity.SysUser;
import cn.cctvcc.system.mapper.SysUserMapper;
import cn.cctvcc.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户信息实现类
 * @author: Jiang
 * @create: 2021-09-22 15:59
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 用户信息列表查询
     * @param sysUser
     * @return
     */
    public List<SysUser> selectList(SysUser sysUser){
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper.setEntity(sysUser);
        return sysUserMapper.selectList(wrapper);
    }

}
