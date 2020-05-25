package cn.tedu.store.service.impl;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            // 是：用户名不存在，允许注册，则执行注册
            addnew(user);
            // -- 返回注册的用户对象
            return user;
        } else {
            // 否：用户存在，抛出DuplicateKeyException异常
            throw new DuplicateKeyException("对不起，您尝试注册的用户名(" + user.getUsername() + ")已存在，请重新尝试！");
        }
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
