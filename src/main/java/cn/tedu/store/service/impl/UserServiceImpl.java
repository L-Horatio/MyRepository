package cn.tedu.store.service.impl;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.exception.*;
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

    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        // 根据参数username查询用户数据
        User data = findByUsername(username);
        // 判断用户数据是否为Null
        if (data == null) {
            // 是：用户名不存在，抛出异常
            throw new UserNotFoundException("登录失败，您尝试登录的用户名不存在，请重试！");
        } else {
            // 否：根据用户名找到了数据，取出盐值
            String salt = data.getSalt();
            // 对参数password执行加密
            String md5Password = getMd5Password(password, salt);
            // 判断密码是否匹配
            if (data.getPassword().equals(md5Password)) {
                // 是：密码正确，则判断是否被删除
                if (data.getIsDelete() == 1) {
                    //   是：已被删除，抛出异常
                    throw new UserNotFoundException("登录失败，您尝试登录的用户名已被删除，请重试！");
                } else {
                    //   否：则登录成功，将第一步查询用户数据中的盐值和密码设置为Null
                    data.setSalt(null);
                    data.setPassword(null);
                    // 返回用户数据
                    return data;
                }
            } else {
                // 否：密码错误，抛出异常
                throw new PasswordNotMatchException("登录失败，您尝试登录的密码错误，请重试！");
            }
        }
    }

    @Override
    public User changePassword(Integer uid, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException {
        // 根据uid查询用户数据
        User data = findById(uid);
        // 判断查询出的数据是否为null
        if (data == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("修改密码失败！您尝试访问的用户数据不存在！");
        }
        // 判断查询结果中的isDelete是否为1
        if (data.getIsDelete() == 1) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("修改密码失败！您尝试访问的用户数据不存在！");
        }
        // 取出查询结果中的盐值
        String salt = data.getSalt();
        // 对参数oldPassword进行md5加密
        String oldMd5Password = getMd5Password(oldPassword, salt);
        // 判断加密后的密码是否与查询的密码匹配
        if (oldMd5Password.equals(data.getPassword())) {
            // 是：对参数newPassword进行md5加密
            String newMd5Password = getMd5Password(newPassword, salt);
            //    获取当前时间
            Date now = new Date();
            //    更新密码
            updatePassword(newMd5Password, data.getUsername(), now, uid);
        } else {
            // 否：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("修改密码失败，您输入的原密码错误，请重新尝试！");
        }
        return data;
    }

    @Override
    public void changeInfo(User user) throws UserNotFoundException, UpdateException {
        // 根据user.getId()查询用户数据
        User data = findById(user.getId());
        // 判断数据是否为null
        if (data == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("修改资料失败，您尝试访问的用户数据不存在！");
        }
        // 判断查询结果中的isDelete是否为1
        if (data.getIsDelete() == 1) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("修改资料失败，您尝试访问的用户已经被删除！");
        }
        // 向参数对象中封装modified_user,modified_time
        user.setModifiedTime(new Date());
        user.setModifiedUser(data.getUsername());
        // 执行修改：gender,phone,email
        updateInfo(user);
    }

    @Override
    public void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
        // 根据uid查询用户数据
        User data = findById(uid);
        // 判断数据是否为null
        if (data == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("修改头像失败，您尝试访问的用户数据不存在！");
        }
        // 判断查询结果中的isDelete是否为1
        if (data.getIsDelete() == 1) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("修改头像失败，您尝试访问的用户已经被删除！");
        }
        // 执行更新头像
        String modifiedUser = data.getUsername();
        Date modifiedTime = new Date();
        updateAvaar(avatar, modifiedUser, modifiedTime, uid);
    }

    @Override
    public User getById(Integer id) {
        User user = findById(id);
        user.setPassword(null);
        user.setSalt(null);
        user.setIsDelete(null);
        return user;
    }

    /*@Override
    public User login(String username, String password) throws UsernameNotFoundException, PasswordNotMatchException {
        // 根据尝试登录的用户名查询数据
        User data = findByUsername(username);
        // 判断用户名是否正确
        if (data.getUsername().equals(username)) {
            // 判断密码是否正确
            if (data.getPassword().equals(password)) {
                // 是：允许登录
                login(username, password);
                return data;
            }
            //      否：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("对不起，您尝试登录的密码错误，请重试！");
        }
        // 否：抛出UsernameNotMatchException异常
        throw new UsernameNotFoundException("对不起，您尝试登录的用户名错误，请重试！");
    }*/

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

    /**
     * 根据id查询数据
     * @param id 用户id
     * @return 匹配的用户数据，如果没有，则返回Null
     */
    private User findById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 修改密码
     * @param password 新密码
     * @param modifiedUser 最后修改人
     * @param modifiedTime 最后修改时间
     * @param uid 用户id
     * @return 受影响的行数
     */
    private void updatePassword(String password, String modifiedUser, Date modifiedTime, Integer uid) {
        Integer rows = userMapper.updatePassword(password, modifiedUser, modifiedTime, uid);
        if (rows != 1) {
            throw new UpdateException("修改密码失败，出现未知错误！");
        }
    }

    /**
     * 修改头像
     * @param avatar 新头像路径
     * @param modifiedUser 最后修改人
     * @param modifiedTime 最后修改时间
     * @param uid 用户id
     * @return 受影响的行数
     */
    private void updateAvaar(String avatar, String modifiedUser, Date modifiedTime, Integer uid) {
        Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新头像失败，出现未知错误！");
        }
    }

    /**
     * 修改个人资料
     * @param user 用户资料
     */
    private void updateInfo(User user){
        Integer rows = userMapper.updateInfo(user);
        if(rows != 1) {
            throw new UpdateException("修改资料失败，出现未知错误！");
        }
    }
}
