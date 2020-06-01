package cn.tedu.store.controller;

import cn.tedu.store.service.exception.*;
import cn.tedu.store.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

        Integer state = null;

        if (e instanceof DuplicateKeyException) {
            // 400-DuplicateKeyException违反了Unique约束掉异常
            state = 400;
        } else if (e instanceof UserNotFoundException) {
            // 401-UsernameNotFoundException用户名不存在异常
            state = 401;
        } else if (e instanceof PasswordNotMatchException) {
            // 402-PasswordNotMatchException密码不匹配异常
            state = 402;
        } else if (e instanceof InsertException) {
            // 500-InsertException插入数据异常
            state = 500;
        } else if (e instanceof UpdateException) {
            // 501-UpdateException更新数据异常
            state = 501;
        }
        return new ResponseResult<>(state, e);
    }

    /**
     * 获取Session中的uid
     * @param session
     * @return
     */
    protected Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
}