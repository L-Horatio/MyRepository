package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/5/25
 * @time 22:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {

    @Autowired
    private IAddressService addressService;

    @Test
    public void creat() {
        String username = "Admin";
        Address address = new Address();
        address.setUid(21);
        address.setPhone("15496546598");
        address.setName("小马同学");
        address.setProvince("610000");
        address.setCity("610700");
        address.setArea("610729");
        Address result = addressService.creat(username, address);
        System.err.println("result=" + result);
    }

    @Test
    public void setDefault() {
        try {
            Integer uid = 7;
            Integer id = 14;
            addressService.setDefault(uid, id);
            System.err.println("OK!");
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().toString());
            System.err.println("错误描述：" + e.getMessage());
        }

    }

    @Test
    public void findByUid() {
        Integer uid = 7;
        List<Address> list = addressService.getListByUid(uid);
        System.err.println("BEGIN");
        for (Address address : list) {
            System.err.println(address);
        }
        System.err.println("END");
    }
}
