package cn.tedu.store.mapper;

/**
 * @author L-Horatio
 * @date 2020/7/20
 * @time 23:28
 */

import cn.tedu.store.entity.Cart;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车数据的持久层接口
 */
public interface CartMapper {

    /**
     * 根据用户id和商品id查询购物车数据
     * @param uid 用户id
     * @param goodsId 商品id
     * @return 匹配的购物车数据，如果没有则返回null
     */
    Cart findByUidAndGid(@Param("uid") Integer uid, @Param("goodsId") Long goodsId);

    /**
     * 新增购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer addnew(Cart cart);

    /**
     * 更新购物车中商品的数量
     * @param id 购物车数据的id
     * @param count 新的数量
     * @return 受影响的行数
     */
    Integer updateCount(@Param("id") Integer id, @Param("count") Integer count);
}
