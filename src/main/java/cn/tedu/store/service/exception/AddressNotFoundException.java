package cn.tedu.store.service.exception;

/**
 * @author L-Horatio
 * @date 2020/6/16
 * @time 21:43
 */

/**
 * 收货地址数据不存在
 */
public class AddressNotFoundException extends ServiceException{

    private static final long serialVersionUID = -6579883714313281294L;

    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
