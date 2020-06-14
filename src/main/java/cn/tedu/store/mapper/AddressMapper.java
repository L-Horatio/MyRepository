package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;

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
     * 根据uid查询收货地址的数量
     * @param uid 用户id
     * @return 收货地址的数量，如果没有数据，返回0
     */
    Integer getCountByUid(Integer uid);
}
