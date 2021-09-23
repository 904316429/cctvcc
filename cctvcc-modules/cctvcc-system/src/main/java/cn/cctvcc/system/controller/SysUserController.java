package cn.cctvcc.system.controller;

import cn.cctvcc.core.domain.R;
import cn.cctvcc.system.api.domain.LoginUser;
import cn.cctvcc.system.api.domain.SysUser;
import cn.cctvcc.system.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * 获取用户信息
     */
    @GetMapping(value = "/userInfo/{userName}")
    public R<LoginUser> userInfo(@PathVariable("userName") String userName) {

        SysUser sysUser = this.sysUserService.selectUserByUserName( userName);

        return R.ok(new LoginUser());
    }

}
