package cn.tedu.store.controller.exception;

/**
 * @author L-Horatio
 * @date 2020/6/11
 * @time 15:43
 */

/**
 * 文件上传异常
 */
public class FileUploadException extends RequestException{

    private static final long serialVersionUID = 2842503069839379467L;

    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
