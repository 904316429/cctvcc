package cn.cctvcc.core.utils;

import cn.cctvcc.core.constant.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 权限获取工具类
 * @author: Jiang
 * @create: 2021-09-22 17:27
 */
public class SecurityUtils {

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.TOKEN_AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 替换token前缀
     */
    public static String replaceTokenPrefix(String token)
    {
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX))
        {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("a123456"));
        System.out.println(matchesPassword("a123456", "$2a$10$hMUQTdp92/k562Rv7PX38.I0IJuUTAaRG16wVWj5Qvedbyg3mf2T6"));
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
