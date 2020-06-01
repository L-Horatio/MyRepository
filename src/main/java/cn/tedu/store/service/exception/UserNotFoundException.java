package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/5/30
 * @time 8:01
 */

/**
 * 用户不存在异常
 */
public class UserNotFoundException extends ServiceException{

    private static final long serialVersionUID = -5843637744941627716L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
