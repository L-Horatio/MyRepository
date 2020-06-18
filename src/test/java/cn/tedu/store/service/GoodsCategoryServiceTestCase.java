package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.exception.ServiceException;
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
public class GoodsCategoryServiceTestCase {

    @Autowired
    private IGoodsCategoryService service;

    @Test
    public void findByParent() {
        Long parentId = 0L;
        List<GoodsCategory> list = service.getByParent(parentId);
        System.err.println("BEGIN");
        for (GoodsCategory category : list) {
            System.err.println(category);
        }
        System.err.println("END");
    }
}
