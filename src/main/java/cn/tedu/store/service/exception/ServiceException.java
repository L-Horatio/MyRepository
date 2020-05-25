package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/5/25
 * @time 19:59
 */

/**
 * 业务异常，是当前项目中所有业务异常掉基类
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 5535533956024880891L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
