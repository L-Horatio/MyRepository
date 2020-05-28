package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/5/25
 * @time 20:29
 */

/**
 * 违反了Unique约束掉异常
 */
public class DuplicateKeyException extends ServiceException{

    private static final long serialVersionUID = -2012612037721768899L;

    public DuplicateKeyException() {
        super();
    }

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }

    protected DuplicateKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
