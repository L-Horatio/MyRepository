package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.GoodsCategory;
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
public class GoodsCategoryMapperTestCase {

    @Autowired
    private GoodsCategoryMapper categoryMapper;
    
    @Test
    public void findByParent() {
        Long parentId = 0L;
        List<GoodsCategory> categories = categoryMapper.findByParent(parentId);
        System.err.println("BEGIN");
        for (GoodsCategory category : categories) {
            System.err.println(category);
        }
        System.err.println("END");
    }
    
}
