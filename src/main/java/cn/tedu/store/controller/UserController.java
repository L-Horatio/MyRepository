package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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

    @RequestMapping("/info.do")
    public ResponseResult<User> getInfo(HttpSession session) {
        // 获取当前登录用户的id
        Integer id = getUidFromSession(session);
        // 执行查询，获取用户数据
        User user = userService.getById(id);
        // 返回
        return new ResponseResult<User>(SUCCESS, user);
    }

    @PostMapping("/change_info.do")
    public ResponseResult<Void> changeInfo(HttpSession session, User user) {
        // 获取当前登录用户的id
        Integer id = getUidFromSession(session);
        // 将id封装的参数user中，因为user是用户提交的，不包含id
        user.setId(id);
        // 执行修改
        userService.changeInfo(user);
        // 返回
        return new ResponseResult<Void>(SUCCESS);
    }
}
