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
public abstract class BaseController {

    // 正确响应代号
    public static final Integer SUCCESS = 200;

    @ExceptionHandler(ServiceException.class)  //表示接下来这个方法是处理异常的
    @ResponseBody  //表示它的响应是正文，而不是转发或者重定向
    public ResponseResult<Void> handleException(Exception e) {
        if (e instanceof DuplicateKeyException) {
            // 400-DuplicateKeyException违反类Unique约束掉异常
            return new ResponseResult<>(400, e);
        } else if (e instanceof InsertException) {
            // 500-InsertException插入数据异常
            return new ResponseResult<>(500, e);
        }
        return null;
    }

}
