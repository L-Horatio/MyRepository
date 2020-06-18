package cn.tedu.store.service.impl;

import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.service.IGoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/18
 * @time 22:46
 */
@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<GoodsCategory> getByParent(Long parentId) {
        return findByParent(parentId);
    }

    /**
     * 根据父级id获取子级的商品分类的数据的列表
     * @param parentId 父级商品的id
     * @return 子级商品分类的数据的列表
     */
    private List<GoodsCategory> findByParent(Long parentId) {
        return goodsCategoryMapper.findByParent(parentId);
    }
}
