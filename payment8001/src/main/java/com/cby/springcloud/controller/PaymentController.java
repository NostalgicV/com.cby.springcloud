package com.cby.springcloud.controller;

import com.cby.springcloud.entities.CommonResult;
import com.cby.springcloud.entities.Payment;
import com.cby.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create( @RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("结果:",i);
        if(i>0){
            return new CommonResult(200,"成功,"+"端口"+serverPort,i);
        }else {
            return new CommonResult(444,"错误",null);
        }

    }
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("结果:"+paymentById+"/t"+"ssss");
        if(paymentById!=null){
            return new CommonResult(200,"成功,"+"端口"+serverPort,paymentById);
        }else {
            return new CommonResult(444,"没有记录"+paymentById,null);
        }

    }
}
