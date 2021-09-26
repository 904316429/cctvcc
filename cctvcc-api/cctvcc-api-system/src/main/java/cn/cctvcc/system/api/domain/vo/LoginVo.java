package cn.cctvcc.system.api.domain.vo;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-24 14:51
 */
public class LoginVo {

    private String access_token;

    private long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
