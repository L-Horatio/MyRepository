package cn.tedu.store.controller.exception;

/**
 * @author L-Horatio
 * @date 2020/6/11
 * @time 15:44
 */

/**
 * 上传文件为空异常
 */
public class FileEmptyException extends FileUploadException{

    private static final long serialVersionUID = -8562962958943496625L;

    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
