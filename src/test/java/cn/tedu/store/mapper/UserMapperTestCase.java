package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author L-Horatio
 * @date 2020/5/24
 * @time 19:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void addnew(){
        User user = new User();
        Date now = new Date();
        user.setUsername("root1");
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
        Integer rows = userMapper.addnew(user);
        System.err.println("rows=" + rows);
    }

    @Test
    public void findByUserName(){
        String username = "root";
        User user = userMapper.findByUsername(username);
        System.err.println(user);
    }

    @Test
    public void findById(){
        Integer id = 5;
        User user = userMapper.findById(id);
        System.err.println(user);
    }

    @Test
    public void updatePassword() {
        String password = "123456";
        String modifiedUser = "admin";
        Date modifiedTime = new Date();
        Integer uid = 6;
        Integer rows = userMapper.updatePassword(password, modifiedUser, modifiedTime, uid);
        System.err.println("rows=" + rows);
    }

    @Test
    public void updateInfo() {
        User user = new User();
        user.setId(33);
        user.setPhone("18691642666");
        user.setEmail("root@163.com");
        user.setGender(1);
        user.setModifiedUser("Admin");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfo(user);
        System.err.println("rows=" + rows);
    }

    @Test
    public void updateAvatar() {
        Integer uid = 30;
        String avatar = "4654564.jpg";
        String modifiedUser = "Admin";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }
}
