package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;

/**
 * @author L-Horatio
 * @date 2020/5/24
 * @time 18:10
 */

/**
 * 处理用户数据等持久层
 */
public interface UserMapper {

    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响等行数
     */
    Integer addnew(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配等用户数据，如果没有，则返回Null
     */
    User findByUsername(String username);
}
