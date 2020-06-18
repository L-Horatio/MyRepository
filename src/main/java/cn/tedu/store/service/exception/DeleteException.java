package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/6/17
 * @time 20:55
 */

/**
 * 删除收货地址异常
 */
public class DeleteException extends ServiceException{

    private static final long serialVersionUID = 2672685219124995958L;

    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
