package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author L-Horatio
 * @date 2020/5/28
 * @time 22:54
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    /**
     * 上传文件夹的名称
     */
    private static final String UPLOAD_DIR_NAME = "upload";

    /**
     * 上传文件的最大取值
     */
    private static final long FILE_MAX_SIZE = 5 * 1024 * 1024;

    /**
     * 允许上传的文件类型
     */
    private static final List<String> FILE_CONTENT_TYPES = new ArrayList<>();

    /**
     * 初始化允许上传的文件类型的集合
     */
    static {
        FILE_CONTENT_TYPES.add("image/jpeg");
        FILE_CONTENT_TYPES.add("image/png");
    }

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

    @PostMapping("/upload.do")
    public ResponseResult<String> handleUpload(
            HttpSession session,
            @RequestParam("file") MultipartFile file) {
        // 检查是否存在上传文件 > file.isEmpty()
        if (file.isEmpty()) {
            // 抛出异常，文件不能为空

        }
        // 检查文件类型 > file.getSize()
        if (file.getSize() > FILE_MAX_SIZE) {
            // 抛出异常，文件大小超出限制
        }
        // 检查文件大小 > file.getContentType()
        if (!FILE_CONTENT_TYPES.contains(file.getContentType())) {
            // 抛出异常，文件类型限制
        }
        // 确定上传文件夹的路径 > session.getServletContext.getRealPath()
        String parentPath = session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
        File parent = new File(parentPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 确定文件名 > getOriginalFileName()
        String originalFileName = file.getOriginalFilename();
        int beginIndex = originalFileName.lastIndexOf(".");
        String suffix = originalFileName.substring(beginIndex);
        String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(900000) + 100000) + suffix;
        // 确定文件
        File dest = new File(parent, fileName);
        // 执行保存文件
        try {
            file.transferTo(dest);
            System.err.println("上传完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取当前用户的Id
        Integer uid = getUidFromSession(session);
        // 更新头像数据
        String avatar = UPLOAD_DIR_NAME + "/" + fileName;
        userService.changeAvatar(uid, avatar);
        // 返回
        ResponseResult<String> rr = new ResponseResult<>();
        rr.setState(SUCCESS);
        rr.setData(UPLOAD_DIR_NAME + "/" + fileName);
        return rr;
    }
}
