package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
    public void getCountByUid(){
        Integer uid = 2;
        Integer count = addressMapper.getCountByUid(uid);
        System.err.println("count=" + count);
    }
}
