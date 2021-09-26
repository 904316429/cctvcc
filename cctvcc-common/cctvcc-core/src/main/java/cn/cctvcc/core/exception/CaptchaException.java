package cn.cctvcc.core.exception;

/**
 * @description: 验证码错误异常类
 * @author: Jiang
 * @create: 2021-09-23 22:18
 */
public class CaptchaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
