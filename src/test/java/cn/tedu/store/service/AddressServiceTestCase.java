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
        address.setName("小张同学");
        Address result = addressService.creat(username, address);
        System.err.println("result=" + result);
    }
}
