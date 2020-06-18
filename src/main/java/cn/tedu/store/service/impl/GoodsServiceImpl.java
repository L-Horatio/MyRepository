package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/19
 * @time 0:56
 */

/**
 * 商品数据的实现类
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getByCategory(Long categoryId, Integer offset, Integer count) {
        return findByCategory(categoryId, offset, count);
    }

    /**
     * 根据商品分类查询商品列表
     * @param categoryId 商品分类的id
     * @param offset 偏移量
     * @param count 获取的数据的最大数
     * @return 商品列表
     */
    private List<Goods> findByCategory(Long categoryId, Integer offset, Integer count) {
        return goodsMapper.findByCategory(categoryId, offset, count);
    }


}
