package cn.tedu.store.mapper;

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
 * @date 2020/5/24
 * @time 19:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        String parent = "86";
        List<District> result = districtMapper.findByParent(parent);
        System.err.println("START");
        for (District district : result) {
            System.err.println(district);
        }
        System.err.println("END");
    }

    @Test
    public void findByCode(){
        String code = "110000";
        District district = districtMapper.findByCode(code);
        System.err.println("district=" + district);
    }
}
