package cn.cctvcc.security.service;

import cn.cctvcc.core.constant.CacheConstants;
import cn.cctvcc.core.constant.Constants;
import cn.cctvcc.core.utils.RSAUtil;
import cn.cctvcc.core.utils.SecurityUtils;
import cn.cctvcc.redis.service.RedisService;
import cn.cctvcc.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @description: 登录密码加密
 * @author: Jiang
 * @create: 2021-09-26 16:54
 */
@Component
public class KeysService {

    @Autowired
    private RedisService redisService;

    private final static long EXPIRE_TIME = Constants.PRIVATE_KEY_EXPIRE; // 私钥过期时间 30S

    private final static String ACCESS_PRIVATE_KEY = CacheConstants.LOGIN_PRIVATE_KEY;

    /**
     * 创建 RSA公私钥匙
     * @param username
     * @return
     * @throws Exception
     */
    public String createKeys(String username) throws Exception{
        RSAUtil.KeyStore keys = RSAUtil.createKeys();
        // 缓存私钥
        redisService.setCacheObject(ACCESS_PRIVATE_KEY + username,
                Base64.getEncoder().encodeToString(RSAUtil.getPrivateKey(keys)),
                EXPIRE_TIME, TimeUnit.SECONDS);
        return  Base64.getEncoder().encodeToString(RSAUtil.getPublicKey(keys));// 返回公钥
    }

    /**
     * 校验密码是否正确
     */
    public boolean verifyPassword(String password, SysUser sysUser) throws Exception{
        // 获取缓存私钥
        String privateKey = redisService.getCacheObject( ACCESS_PRIVATE_KEY+sysUser.getUserName());
        // 得到解密后的密码
        password = new String(RSAUtil.decryptByPrivateKey(
                Base64.getDecoder().decode(password),
                Base64.getDecoder().decode(privateKey)));
        if (!SecurityUtils.matchesPassword(password, sysUser.getPassword()))
            return false;
        return true;
    }

}
