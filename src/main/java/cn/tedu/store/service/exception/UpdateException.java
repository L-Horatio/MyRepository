package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/5/30
 * @time 22:08
 */

/**
 * 更新数据异常
 */
public class UpdateException extends ServiceException{

    private static final long serialVersionUID = 6228521275036190142L;

    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
