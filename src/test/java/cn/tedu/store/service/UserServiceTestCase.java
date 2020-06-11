package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author L-Horatio
 * @date 2020/5/25
 * @time 22:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {

    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            Date now = new Date();
            user.setUsername("spring456");
            user.setPassword("1234");
            user.setGender(1);
            user.setPhone("13546548546");
            user.setEmail("root@163.com");
            user.setSalt("Hello,MD5!");
            User result = userService.reg(user);
            System.err.println("result=" + result);
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void login(){
        try {
            String username = "spring4";
            String password = "1234";
            User result = userService.login(username, password);
            System.err.println("result=" + result);
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void changePassword(){
        try {
            Integer uid = 29;
            String oldPassword = "123456";
            String newPassword = "1234";
            userService.changePassword(uid, oldPassword, newPassword);
            System.err.println("OK!");
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void changeAvatar(){
        try {
            Integer uid = 31;
            String avatar = "upload/52546642134.jpg";
            userService.changeAvatar(uid, avatar);
            System.err.println("OK!");
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void changeInfo(){
        try {
            User user = new User();
            user.setId(32);
            user.setPhone("18612324545");
            user.setEmail("rootw@163.com");
            user.setGender(0);
            userService.changeInfo(user);
            System.err.println("OK!");
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }
}
