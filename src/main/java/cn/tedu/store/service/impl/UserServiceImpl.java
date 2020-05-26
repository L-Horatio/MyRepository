package cn.tedu.store.service.impl;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author L-Horatio
 * @date 2020/5/25
 * @time 20:38
 */

/**
 * 处理用户数据掉业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;  //声明持久层对象

    @Override
    public User reg(User user) throws DuplicateKeyException, InsertException {
        // 根据尝试注册的用户名查询用户数据
        User data = findByUsername(user.getUsername());
        // 判断查询到的数据是否为Null
        if(data == null) {
            // 是：用户名不存在，允许注册，则处理密码加密

            // 补充非用户提交的数据
            user.setIsDelete(0); // 是否已经删除：否
            // 添加四项日志
            Date now = new Date();
            user.setCreatedUser(user.getUsername());
            user.setCreatedTime(now);
            user.setModifiedUser(user.getUsername());
            user.setModifiedTime(now);
            // 加密-1：获取随即的UUID作为盐值
            String salt = UUID.randomUUID().toString();
            // 加密-2：把用户自己填写的密码取出来
            String srcPassword = user.getPassword();
            // 加密-3：用md5加密，得到一个新密码(可能多处使用，就需要写一个专门加密的方法)
            String md5Password = getMd5Password(srcPassword, salt);
            // 加密-4：把新密码重新封装到user对象中
            user.setPassword(md5Password);
            // 加密-5：将盐值封装到user对象(盐值必须存进去)
            user.setSalt(salt);
            // 执行注册
            addnew(user);
            // -- 返回注册的用户对象
            return user;
        } else {
            // 否：用户存在，抛出DuplicateKeyException异常
            throw new DuplicateKeyException("对不起，您尝试注册的用户名(" + user.getUsername() + ")已存在，请重新尝试！");
        }
    }

    /**
     * 对用户提交的密码进行md5加密
     * @param srcPassword 用户提交的密码
     * @param salt 盐值
     * @return
     */
    private String getMd5Password(String srcPassword, String salt) {
        // md5加密的规则(这个规则可以自己设置)
        // 盐值 拼接 原密码 拼接 盐值
        String str = salt + srcPassword + salt;
        // 循环执行10次摘要运算
        for(int i = 0; i < 10; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes());
        }
        // 返回摘要结果
        return str;
    }

    /**
     * 插入用户数据
     * @param user 用户数据
     * @throws InsertException
     */
    private void addnew(User user){
        Integer rows = userMapper.addnew(user);
        if(rows != 1) {
            throw new InsertException("增加用户数据时出现未知错误！");
        }
    }

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配等用户数据，如果没有，则返回Null
     */
    private User findByUsername(String username){
        return userMapper.findByUsername(username);
    }
}
