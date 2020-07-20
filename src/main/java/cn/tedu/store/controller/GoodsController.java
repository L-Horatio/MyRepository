package cn.tedu.store.controller;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;
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
@RequestMapping("/goods")
public class GoodsController extends BaseController{

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/list/{categoryId}")
    public ResponseResult<List<Goods>> getByParent(@PathVariable("categoryId") Long categoryId) {
        List<Goods> list = goodsService.getByCategory(categoryId, 0, 20);
        return new ResponseResult<List<Goods>>(SUCCESS, list);
    }

    @GetMapping("/details/{id}")
    public ResponseResult<Goods> getById(@PathVariable("id") Long id) {
        Goods goods = goodsService.getById(id);
        return new ResponseResult<Goods>(SUCCESS, goods);
    }

    @GetMapping("/hot")
    public ResponseResult<List<Goods>> getHotGoods() {
        List<Goods> list = goodsService.getByPriority(4);
        return new ResponseResult<List<Goods>>(SUCCESS, list);
    }
}
