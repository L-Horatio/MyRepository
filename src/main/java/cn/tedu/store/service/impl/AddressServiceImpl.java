package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/14
 * @time 22:17
 */
@Service
public class AddressServiceImpl implements IAddressService {

    String provinceName = null;
    String cityName = null;
    String areaName = null;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

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
        // 处理district；根据省/市/区的代号获取District的值
        String district = getDistrict(address.getProvince(), address.getCity(), address.getArea());
        address.setDistrict(district);
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

    @Override
    public void setDefault(Integer uid, Integer id) {
        updateNonDefault(uid);
        updateDefault(id);
    }

    @Override
    public List<Address> getListByUid(Integer uid) {
        return findByUid(uid);
    }

    /**
     * 根据省/市/区的代号获取名称
     * @param province 省的代号
     * @param city 市的代号
     * @param area 区的代号
     * @return 返回省/市/区的名称
     */
    private String getDistrict(String province, String city, String area) {
        District p = districtService.getByCode(province);
        District c = districtService.getByCode(city);
        District a = districtService.getByCode(area);

        if (p != null) {
            provinceName = p.getName();
        }

        if (c != null) {
            cityName = c.getName();
        }

        if (a != null) {
            areaName = a.getName();
        }
        return provinceName + "," + cityName + "," + areaName;
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
     * 将某用户的收货地址全部设置为非默认
     * @param uid 用户的id
     * @return 受影响的行数
     */
    private void updateNonDefault(Integer uid) {
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("修改默认地址失败，出现未知错误！");
        }
    }

    /**
     * 将指定id的收货地址设置为默认
     * @param id 收货地址数据的id
     * @return 受影响的行数
     */
    private void updateDefault(Integer id) {
        Integer rows = addressMapper.updateDefault(id);
        if (rows != 1) {
            throw new UpdateException("修改默认地址失败，出现未知错误！");
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

    /**
     * 根据uid查询某用户的收货地址
     * @param uid 用户的id
     * @return 收货地址
     */
    private List<Address> findByUid(Integer uid) {
        return addressMapper.findByUid(uid);
    }
}
