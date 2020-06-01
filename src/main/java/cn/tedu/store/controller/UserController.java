package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author L-Horatio
 * @date 2020/5/28
 * @time 22:54
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @PostMapping("/reg.do")
    public ResponseResult<Void> handleReg(User user) {
        userService.reg(user);
        return new ResponseResult<Void>(SUCCESS);
    }

    @PostMapping("/login.do")
    public ResponseResult<Void> handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {
        // 执行登录
        User user = userService.login(username, password);
        // 将相关信息存入到session
        session.setAttribute("uid", user.getId());
        session.setAttribute("username", user.getUsername());
        // 返回结果
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("/password.do")
    public ResponseResult<Void> changePassword(
            HttpSession session,
            @RequestParam("old_password") String oldPassword,
            @RequestParam("new_password") String newPassword
            ) {
        // 获取当前登陆的用户的id
        Integer uid = getUidFromSession(session);
        // 执行修改密码
        userService.changePassword(uid, oldPassword, newPassword);
        // 返回结果
        return new ResponseResult<Void>(SUCCESS);
    }
}
