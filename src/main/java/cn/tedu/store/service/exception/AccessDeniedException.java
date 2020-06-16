package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/6/16
 * @time 21:45
 */

/**
 * 访问权限不通过异常
 */
public class AccessDeniedException extends ServiceException{

    private static final long serialVersionUID = -7701754769707579519L;

    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
