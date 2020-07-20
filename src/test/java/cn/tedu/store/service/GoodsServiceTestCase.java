package cn.tedu.store.service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
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
public class GoodsServiceTestCase {

    @Autowired
    private IGoodsService service;

    @Test
    public void getByCategory() {
        Long categoryId = 163L;
        Integer offset = 0;
        Integer count = 10;
        List<Goods> list = service.getByCategory(categoryId, offset, count);
        System.err.println("BEGIN");
        for (Goods goods : list) {
            System.err.println(goods);
        }
        System.err.println("END");
    }
    
    @Test
    public void getBiId() {
        Long id = 10000022L;
        Goods data = service.getById(id);
        System.err.println(data);
    }

    @Test
    public void getByPriority() {
        Integer count = 4;
        List<Goods> list = service.getByPriority(count);
        System.err.println("BEGIN");
        for (Goods data : list) {
            System.err.println(data);
        }
        System.err.println("END");
    }
}
