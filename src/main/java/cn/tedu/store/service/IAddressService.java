package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;

import java.util.List;

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

    /**
     * 设置默认收货地址
     * @param uid 收货地址归属的用户的id
     * @param id 将要设置为默认收货地址的数据的id
     */
    void setDefault(Integer uid, Integer id) throws UpdateException;

    /**
     * 获取某用户的收货地址列表
     * @param uid 用户的id
     * @return 收货地址列表
     */
    List<Address> getListByUid(Integer uid);
}
