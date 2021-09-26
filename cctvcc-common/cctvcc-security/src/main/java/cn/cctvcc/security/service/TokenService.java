package cn.cctvcc.security.service;

import cn.cctvcc.core.constant.CacheConstants;
import cn.cctvcc.core.constant.Constants;
import cn.cctvcc.core.utils.IdUtils;
import cn.cctvcc.core.utils.SecurityUtils;
import cn.cctvcc.core.utils.ServletUtils;
import cn.cctvcc.core.utils.ip.IpUtils;
import cn.cctvcc.redis.service.RedisService;
import cn.cctvcc.system.api.domain.LoginUser;
import cn.cctvcc.system.api.domain.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: token验证处理
 * @author: Jiang
 * @create: 2021-09-24 11:38
 */
@Component
public class TokenService {

    @Autowired
    private RedisService redisService;

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60; // Token过期时间 30 * 60 = 30分钟

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    protected static final long MILLIS_SECOND = 1000;

    /**
     * 创建令牌
     *
     * @param loginUser
     */
    public LoginVo createToken(LoginUser loginUser) {

        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        loginUser.setUserid(loginUser.getSysUser().getUserId());
        loginUser.setUsername(loginUser.getSysUser().getUserName());
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginUser);// 刷新令牌时间

        // 保存或更新用户token
        LoginVo loginVo = new LoginVo();
        loginVo.setAccess_token(token);
        loginVo.setExpires_in(EXPIRE_TIME);
        redisService.setCacheObject(ACCESS_TOKEN + token, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
        return loginVo;
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME * MILLIS_SECOND);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        return getLoginUser(token);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            LoginUser user = redisService.getCacheObject(userKey);
            return user;
        }
        return null;
    }

    /**
     * 删除用户缓存记录
     *
     * @param token
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisService.deleteObject(userKey);
        }
    }

    private String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }


}
