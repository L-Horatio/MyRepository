package cn.tedu.store.service;

/**
 * @author L-Horatio
 * @date 2020/6/18
 * @time 22:44
 */

import cn.tedu.store.entity.GoodsCategory;

import java.util.List;

/**
 * 商品分类数据的业务层接口
 */
public interface IGoodsCategoryService {

    /**
     * 根据父级id获取子级的商品分类的数据的列表
     * @param parentId 父级商品的id
     * @return 子级商品分类的数据的列表
     */
    List<GoodsCategory> getByParent(Long parentId);
}
