package cn.tedu.store.service.impl;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.srevice.IUserService;
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
    private UserMapper userMapper;

    @Override
    public User reg(User user) throws DuplicateKeyException, InsertException {
        // 根据尝试注册掉用户名查询用户数据
        // 判断查询到掉数据是否为Null
        // 是：用户名不存在，允许注册，则执行注册
        // -- 返回注册掉用户对象
        // 否：用户存在，抛出DuplicateKeyException异常
        return null;
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
