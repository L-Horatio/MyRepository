package cn.tedu.store.mapper;

import cn.tedu.store.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/19
 * @time 0:42
 */

/**
 * 商品数据的持久层接口
 */
public interface GoodsMapper {

    /**
     * 根据商品分类查询商品列表
     * @param categoryId 商品分类的id
     * @param offset 偏移量
     * @param count 获取的数据的最大数
     * @return 商品列表
     */
    List<Goods> findByCategory(@Param("categoryId") Long categoryId, @Param("offset") Integer offset, @Param("count") Integer count);
}
