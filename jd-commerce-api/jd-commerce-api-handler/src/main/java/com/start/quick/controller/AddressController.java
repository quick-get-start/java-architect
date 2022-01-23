package com.start.quick.controller;

import com.start.quick.code.AddressResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.UserAddress;
import com.start.quick.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
