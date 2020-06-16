package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;

import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/14
 * @time 21:30
 */
public interface AddressMapper {

    /**
     * 增加收货地址
     * @param address 用户的收货地址
     * @return 受影响的行数
     */
    Integer addnew(Address address);

    /**
     * 将某用户的收货地址全部设置为非默认
     * @param uid 用户的id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 将指定id的收货地址设置为默认
     * @param id 收货地址数据的id
     * @return 受影响的行数
     */
    Integer updateDefault(Integer id);

    /**
     * 根据uid查询收货地址的数量
     * @param uid 用户id
     * @return 收货地址的数量，如果没有数据，返回0
     */
    Integer getCountByUid(Integer uid);

    /**
     * 获取某用户的收货地址列表
     * @param uid 用户的id
     * @return 收货地址列表
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据id查询收货地址数据
     * @param id 收货地址的id
     * @return 匹配的数据，没有则返回null
     */
    Address findById(Integer id);
}
