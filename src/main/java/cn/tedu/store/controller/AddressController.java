package cn.tedu.store.controller;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.ResponseResult;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author L-Horatio
 * @date 2020/6/15
 * @time 14:57
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{

    @Autowired
    private IAddressService addressService;

    @PostMapping("/create")
    public ResponseResult<Void> handleCreate(Address address, HttpSession session) {
        // 根据session获取username
        String username = session.getAttribute("username").toString();
        // 根据session获取uid
        Integer uid = getUidFromSession(session);
        // 将uid封装到address中
        address.setUid(uid);
        // 调用业务层对象执行创建收货地址
        addressService.creat(username, address);
        // 返回
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("/district")
    public ResponseResult<Void> getDefault(HttpSession session, Integer id) {
        // 根据session获取uid
        Integer uid = getUidFromSession(session);
        addressService.setDefault(uid, id);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("/list")
    public ResponseResult<List<Address>> getListByUid(HttpSession session) {
        // 根据session获取uid
        Integer uid = getUidFromSession(session);
        // 根据uid查询出用户的收货地址
        List<Address> list = addressService.getListByUid(uid);
        // 返回
        return new ResponseResult<List<Address>>(SUCCESS, list);
    }
}
