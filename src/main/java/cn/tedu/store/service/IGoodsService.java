package cn.tedu.store.service;

/**
 * @author L-Horatio
 * @date 2020/6/19
 * @time 0:55
 */

import cn.tedu.store.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品数据的业务层接口
 */
public interface IGoodsService {

    /**
     * 根据商品分类查询商品列表
     * @param categoryId 商品分类的id
     * @param offset 偏移量
     * @param count 获取的数据的最大数
     * @return 商品列表
     */
    List<Goods> getByCategory(Long categoryId, Integer offset, Integer count);

    /**
     * 根据id查询商品详情
     * @param id 商品的id
     * @return 商品详情，如果没有匹配数据，返回Null
     */
    Goods getById(Long id);

    /**
     * 根据优先级获取商品数据的列表
     * @param count 获取商品的数量
     * @return 优先级最高的几个商品数据的列表
     */
    List<Goods> getByPriority(Integer count);
}
