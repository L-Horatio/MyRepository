package cn.tedu.store.controller.exception;

/**
 * @author L-Horatio
 * @date 2020/6/11
 * @time 15:45
 */

/**
 * 上传文件大小超出限制异常
 */
public class FileSizeOutOfLimitException extends FileUploadException{

    private static final long serialVersionUID = 6749306732962349139L;

    public FileSizeOutOfLimitException() {
    }

    public FileSizeOutOfLimitException(String message) {
        super(message);
    }

    public FileSizeOutOfLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeOutOfLimitException(Throwable cause) {
        super(cause);
    }

    public FileSizeOutOfLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
