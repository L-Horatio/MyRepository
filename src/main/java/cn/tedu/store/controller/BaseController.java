package cn.tedu.store.controller;

import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.ServiceException;
import cn.tedu.store.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author L-Horatio
 * @date 2020/5/26
 * @time 22:22
 */
public class BaseController {

    /**
     * 正确响应时的代号
     */
    private static final Integer SUCCESS = 200;

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult<Void> handleException (Exception e) {
        if (e instanceof DuplicateKeyException) {
            // 400-DuplicateKeyException违反了Unique约束掉异常
            return new ResponseResult<>(400, e);
        } else if (e instanceof InsertException) {
            // 500-InsertException插入数据异常
            return new ResponseResult<>(500, e);
        }
        return null;
    }
}
