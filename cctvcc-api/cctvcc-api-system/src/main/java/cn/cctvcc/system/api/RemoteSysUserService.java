package cn.cctvcc.system.api;

import cn.cctvcc.core.constant.ServiceNameConstants;
import cn.cctvcc.core.domain.R;
import cn.cctvcc.system.api.domain.LoginUser;
import cn.cctvcc.system.api.factory.RemoteSysUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 用户服务
 * @author: Jiang
 * @create: 2021-09-23 16:03
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteSysUserFallbackFactory.class)
public interface RemoteSysUserService {

    /**
     * 获取用户信息
     */
    @GetMapping(value = "sysUser/userInfo/{userName}")
    R<LoginUser> userInfo(@PathVariable("userName") String userName);

}
