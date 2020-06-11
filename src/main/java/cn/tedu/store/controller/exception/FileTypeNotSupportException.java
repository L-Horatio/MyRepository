package cn.tedu.store.controller.exception;

/**
 * @author L-Horatio
 * @date 2020/6/11
 * @time 15:46
 */

/**
 * 上传的文件类型不匹配异常
 */
public class FileTypeNotSupportException extends FileUploadException{

    private static final long serialVersionUID = -2059227218607057287L;

    public FileTypeNotSupportException() {
    }

    public FileTypeNotSupportException(String message) {
        super(message);
    }

    public FileTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeNotSupportException(Throwable cause) {
        super(cause);
    }

    public FileTypeNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
