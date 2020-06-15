package cn.tedu.store.controller;

import cn.tedu.store.entity.District;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/15
 * @time 16:29
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @RequestMapping("/list")
    public ResponseResult<List<District>> getListByParent(@RequestParam("parent") String parent) {
        List<District> list = districtService.getListByParent(parent);
        return new ResponseResult<List<District>>(SUCCESS, list);
    }
}
