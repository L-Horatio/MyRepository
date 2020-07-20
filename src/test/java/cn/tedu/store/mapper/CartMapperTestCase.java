package cn.tedu.store.mapper;

import cn.tedu.store.entity.Cart;
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
public class CartMapperTestCase {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void findByUidAndGid() {
        Long goodsId = 100L;
        Integer uid = 1;
        Cart cart = cartMapper.findByUidAndGid(uid, goodsId);
        System.err.println(cart);
    }
    
    @Test
    public void addnew() {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setGid(100L);
        cart.setCount(21);
        cart.setPrice(2689L);
        Integer rows = cartMapper.addnew(cart);
        System.err.println("rows=" + rows);
    }

    @Test
    public void updateCount() {
        Integer id = 1;
        Integer count = 5;
        Integer rows = cartMapper.updateCount(id, count);
        System.err.println("rows=" + rows);
    }
}
