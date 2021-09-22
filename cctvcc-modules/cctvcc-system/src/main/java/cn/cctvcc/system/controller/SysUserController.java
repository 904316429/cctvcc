package cn.cctvcc.system.controller;

import cn.cctvcc.core.web.domain.AjaxResult;
import cn.cctvcc.system.domain.entity.SysUser;
import cn.cctvcc.system.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户信息控制层
 * @author: Jiang
 * @create: 2021-09-22 14:56
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 用户信息列表查询
     * @param sysUser
     * @return
     */
    @GetMapping( value="/list")
    public AjaxResult list(SysUser sysUser){
        List<SysUser> list = sysUserService.selectList(sysUser);
        return AjaxResult.success(list);
    }
}
