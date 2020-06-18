package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void setDefault(Integer uid, Integer id) {
        // 根据id查询收货地址数据
        Address data = findById(id);
        // 判断数据是否为null
        if (data == null) {
            throw new AddressNotFoundException("设置默认收货地址失败，尝试访问的收货地址数据不存在！");
        }
        // 判断查询到到数据中的uid与参数uid是否一致
        if (data.getUid() != uid) {
            throw new AccessDeniedException("设置默认收货地址失败，访问数据权限不通过！");
        }

        // 将该用户的所有收货地址设置为非默认
        updateNonDefault(uid);
        // 将制定id的收货地址设为默认
        updateDefault(id);
    }

    @Override
    public List<Address> getListByUid(Integer uid) {
        return findByUid(uid);
    }

    @Override
    @Transactional
    public void deleteDefault(Integer uid, Integer id) throws DeleteException {
        // 根据id查询收货地址数据
        Address data = findById(id);
        // 判断是否为null
        if (data == null) {
            //是：抛出异常AddressNotFoundException
            throw new AddressNotFoundException("删除失败，删除的数据不存在！");
        }
        // 检查数据归属是否有误
        if (data.getUid() != uid) {
            // 是：抛出异常
            throw new AccessDeniedException("删除失败，访问权限不通过！");
        }
        // 执行删除
        deleteById(id);
        // 检查删除后是否还有收货地址
        //Integer count = addressMapper.getCountByUid(uid);
        if (getCountByUid(uid) > 0) {
            // 是：判断刚才删除的收货地址是否为默认
            if (data.getIsDefault() == 1) {
                // --是：查询出最后修改的收货地址为默认
                Integer lastModifiedId = findLastModified(uid).getId();
                setDefault(uid, lastModifiedId);
            }
        }
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

    /**
     * 根据id查询收货地址数据
     * @param id 收货地址的id
     * @return 匹配的数据，没有则返回null
     */
    private Address findById(Integer id) {
        return addressMapper.findById(id);
    }

    /**
     * 根据uid查询出最后一条修改数据
     * @param uid 用户id
     * @return 匹配非数据，没有则返回null
     */
    private Address findLastModified(Integer uid) {
        return addressMapper.findLastModified(uid);
    }

    /**
     * 根据收货地址的id删除数据
     * @param id 收货地址的id
     */
    private void deleteById(Integer id) {
        Integer rows = addressMapper.deleteById(id);
        if (rows == 1) {
            throw new DeleteException("删除数据失败，出现未知错误！");
        }
    }
}
