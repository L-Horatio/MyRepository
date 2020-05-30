package cn.tedu.store.controller;

import cn.tedu.store.service.exception.*;
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
    public static final Integer SUCCESS = 200;

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult<Void> handleException (Exception e) {
        if (e instanceof DuplicateKeyException) {
            // 400-DuplicateKeyException违反了Unique约束掉异常
            return new ResponseResult<>(400, e);
        } else if (e instanceof UserNotFoundException) {
            // 401-UsernameNotFoundException用户名不存在异常
            return new ResponseResult<>(401, e);
        } else if (e instanceof PasswordNotMatchException) {
            // 402-PasswordNotMatchException密码不匹配异常
            return new ResponseResult<>(402, e);
        } else if (e instanceof InsertException) {
            // 500-InsertException插入数据异常
            return new ResponseResult<>(500, e);
        }
        return null;
    }
}
