package cn.tedu.store.mapper;

/**
 * @author L-Horatio
 * @date 2020/6/18
 * @time 22:27
 */

import cn.tedu.store.entity.GoodsCategory;

import java.util.List;

/**
 * 商品分类数据的持久层接口
 */
public interface GoodsCategoryMapper {

    /**
     * 根据父级id获取子级的商品分类的数据的列表
     * @param parentId 父级商品的id
     * @return 子级商品分类的数据的列表
     */
    List<GoodsCategory> findByParent(Long parentId);
}
