package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.*;

/**
 * @author L-Horatio
 * @date 2020/5/25
 * @time 19:56
 */

/**
 * 处理用户数据掉业务层接口
 */
public interface IUserService {

    /**
     * 用户注册
     * @param user 用户的注册信息
     * @return 成功注册的用户数据
     * @throws DuplicateKeyException
     * @throws InsertException
     */
    User reg(User user) throws DuplicateKeyException, InsertException;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 返回成功登录的用户数据
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     */
    User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;

    /**
     * 修改密码
     * @param uid 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws UpdateException
     * @return
     */
    User changePassword(Integer uid, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException;

    /**
     * 修改用户个人资料
     * @param user 用户数据
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    void changeInfo(User user) throws UserNotFoundException, UpdateException;

    /**
     * 根据id获取用户数据
     * @param id 用户id
     * @return 匹配的用户数据，没有就返回null
     */
    User getById(Integer id) throws UserNotFoundException, UpdateException;
}
