package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/5/24
 * @time 19:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void addnew(){
        Address address = new Address();
        address.setName("zhangsan3");
        address.setPhone("18691622455");
        address.setTag("公司");
        address.setUid(23);
        Integer rows = addressMapper.addnew(address);
        System.err.println("rows=" + rows);
        System.err.println(address);
    }

    @Test
    public void updateNonDefault() {
        Integer uid = 7;
        Integer rows = addressMapper.updateNonDefault(uid);
        System.err.println("rows=" + rows);
    }

    @Test
    public void updateDefault() {
        Integer id = 15;
        Integer rows = addressMapper.updateDefault(id);
        System.err.println("rows=" + rows);
    }

    @Test
    public void getCountByUid(){
        Integer uid = 2;
        Integer count = addressMapper.getCountByUid(uid);
        System.err.println("count=" + count);
    }

    @Test
    public void findByUid() {
        Integer uid = 7;
        List<Address> list = addressMapper.findByUid(uid);
        System.err.println("BEGIN");
        for (Address address : list) {
            System.err.println(address);
        }
        System.err.println("END");
    }

}
