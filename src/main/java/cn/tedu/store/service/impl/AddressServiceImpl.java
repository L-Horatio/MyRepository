package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author L-Horatio
 * @date 2020/6/14
 * @time 22:17
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Address creat(String username, Address address) throws InsertException {
        // 通过address.getUid()得到用户id,并以此查询用户的收货地址数量
        Integer uid = address.getUid();
        Integer count = addressMapper.getCountByUid(uid);
        // 判断数量是否为0
//        if (count == 0) {
//            // 是：当前用户首次创建地址，则该地址默认：address.setIsDefault(1)
//            address.setIsDefault(1);
//        } else {
//            // 否：当前用户非首次创建地址，则该地址非默认：address.setIsDefault(0)
//            address.setIsDefault(0);
//        }
        address.setIsDefault(count == 0 ? 1 : 0);
        // TODO 处理district
        // 封装日志
        Date now = new Date();
        address.setModifiedUser(username);
        address.setModifiedTime(now);
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        // 执行创建新地址
        addnew(address);
        return address;
    }

    /**
     * 增加收货地址
     * @param address 用户的收货地址
     */
    private void addnew(Address address) {
        Integer rows = addressMapper.addnew(address);
        if (rows != 1) {
            throw new InsertException("添加收货地址失败，出现未知错误！");
        }
    }

    /**
     * 根据uid查询收货地址的数量
     * @param uid 用户id
     * @return 收货地址的数量，如果没有数据，返回0
     */
    private Integer getCountByUid(Integer uid) {
        return addressMapper.getCountByUid(uid);
    }
}
