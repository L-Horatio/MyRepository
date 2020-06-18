package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Goods;
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
public class GoodsMapperTestCase {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void findByCategory() {
        Long categoryId = 163L;
        Integer offset = 2;
        Integer count = 20;
        List<Goods> list = goodsMapper.findByCategory(categoryId, offset, count);
        System.err.println("BEGIN");
        for (Goods goods : list) {
            System.err.println(goods);
        }
        System.err.println("END");
    }

}
