package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.exception.InsertException;

/**
 * @author L-Horatio
 * @date 2020/6/14
 * @time 22:16
 */

/**
 * 收货地址的业务层接口
 */
public interface IAddressService {

    /**
     * 创建新收货地址
     * @param username 当前执行人
     * @param address 收货地址信息
     * @return 受影响的行数
     * @throws InsertException
     */
    Address creat(String username, Address address) throws InsertException;
}
