package cn.tedu.store.srevice;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;

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
     * @param user 用户掉注册信息
     * @return 成功注册的用户数据
     * @throws DuplicateKeyException
     * @throws InsertException
     */
    User reg(User user) throws DuplicateKeyException, InsertException;

}
