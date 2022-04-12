package com.cby.springcloud.controller;

import com.cby.springcloud.entities.CommonResult;
import com.cby.springcloud.entities.Payment;
import com.cby.springcloud.service.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OderFeignController {
    @Resource
    private OrderFeignService orderFeignService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getOrderByid(@PathVariable("id") Long id){
        return orderFeignService.getPaymentById(id);
    }
}
