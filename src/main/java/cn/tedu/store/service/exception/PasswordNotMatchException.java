package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/5/30
 * @time 8:03
 */

/**
 * 密码不匹配异常
 */
public class PasswordNotMatchException extends ServiceException{

    private static final long serialVersionUID = 3421720783905358548L;

    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
