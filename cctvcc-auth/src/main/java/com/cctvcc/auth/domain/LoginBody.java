package com.cctvcc.auth.domain;

/**
 * @description: 用户登录对象
 * @author: Jiang
 * @create: 2021-09-23 16:12
 */
public class LoginBody {

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
