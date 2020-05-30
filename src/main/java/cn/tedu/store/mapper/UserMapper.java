package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.websocket.Session;
import java.util.Date;

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
     * 根据用户名查询用户数据(为了保证用户名是唯一性)
     * @param username 用户名
     * @return 匹配等用户数据，如果没有，则返回Null
     */
    User findByUsername(String username);

    /**
     * 根据id查询数据
     * @param id 用户id
     * @return 匹配等用户数据，如果没有，则返回Null
     */
    User findById(Integer id);

    /**
     * 修改密码
     * @param password 新密码
     * @param modifiedUser 最后修改人
     * @param modifiedTime 最后修改时间
     * @param uid 用户id
     * @return 受影响的行数
     */
    Integer updatePassword(
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime,
            @Param("uid") Integer uid
    );


}
