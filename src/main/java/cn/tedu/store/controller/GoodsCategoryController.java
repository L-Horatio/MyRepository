package cn.tedu.store.controller;

import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/18
 * @time 22:54
 */
@RestController
@RequestMapping("/category")
public class GoodsCategoryController extends BaseController{

    @Autowired
    private IGoodsCategoryService categoryService;

    @GetMapping("/list/{parent}")
    public ResponseResult<List<GoodsCategory>> getByParent(@PathVariable("parent") Long parent) {
        List<GoodsCategory> list = categoryService.getByParent(parent);
        return new ResponseResult<List<GoodsCategory>>(SUCCESS, list);
    }
}
