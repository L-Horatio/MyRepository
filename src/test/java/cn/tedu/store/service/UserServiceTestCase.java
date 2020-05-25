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
            user.setUsername("root3");
            user.setPassword("1234");
            user.setGender(1);
            user.setPhone("13546548546");
            user.setEmail("root@163.com");
            user.setSalt("Hello,MD5!");
            user.setIsDelete(0);
            user.setCreatedUser("Admin");
            user.setModifiedUser("Admin");
            user.setCreatedTime(now);
            user.setModifiedTime(now);
            User result = userService.reg(user);
            System.err.println("result=" + result);
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }
}
