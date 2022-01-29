package com.start.quick.controller;

import com.start.quick.code.AddressResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.UserAddress;
import com.start.quick.model.UserAddressModel;
import com.start.quick.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public JSONResult<List<UserAddress>> getAll(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.build(AddressResultCode.INVALID_REQUEST_PARAM, "用户不存在");
        }

        List<UserAddress> addresses = this.addressService.findAllByUserId(userId);
        return JSONResult.ok("用户地址查询成功", addresses);
    }

    @PostMapping("add")
    public JSONResult<Void> save(@RequestBody UserAddressModel addressModel) {
        this.addressService.save(addressModel);
        return JSONResult.ok("用户地址创建成功");
    }

    @PostMapping("update/{addressId}")
    public JSONResult<Void> update(@PathVariable String addressId,
                                   @RequestBody UserAddressModel addressModel) {
        this.addressService.update(addressId, addressModel);
        return JSONResult.ok("用户地址更新成功");
    }

    @DeleteMapping("delete")
    public JSONResult<Void> update(@RequestParam String addressId) {
        this.addressService.deleteById(addressId);
        return JSONResult.ok("用户地址删除成功");
    }

    @PostMapping("setDefault")
    public JSONResult<Void> setDefault(@RequestParam String userId,
                                       @RequestParam String addressId) {
        this.addressService.setDefault(userId, addressId);
        return JSONResult.ok("默认地址设置成功");
    }
}
