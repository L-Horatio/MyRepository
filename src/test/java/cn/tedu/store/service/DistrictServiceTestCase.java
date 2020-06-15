package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/5/25
 * @time 22:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTestCase {

    @Autowired
    private IDistrictService districtService;

    @Test
    public void findByParent() {
        String parent = "610000";
        List<District> result = districtService.getListByParent(parent);
        System.err.println("BEGIN");
        for (District districtService : result) {
            System.err.println(districtService);
        }
        System.err.println("END");
    }

    @Test
    public void findByCode() {
        String code = "610000";
        District district = districtService.getByCode(code);
        System.err.println("district=" + district);
    }
}
