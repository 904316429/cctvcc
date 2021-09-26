package cn.cctvcc.core.exception;

/**
 * @description: 工具类异常
 * @author: Jiang
 * @create: 2021-09-24 11:23
 */
public class UtilException extends RuntimeException{

    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e)
    {
        super(e.getMessage(), e);
    }

    public UtilException(String message)
    {
        super(message);
    }

    public UtilException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
