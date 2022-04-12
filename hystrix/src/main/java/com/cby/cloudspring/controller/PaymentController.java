package com.cby.cloudspring.controller;

import com.cby.cloudspring.service.PaymentService;
import io.micrometer.core.instrument.Meter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String s = paymentService.payment_ok(id);
        return s;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        String s = paymentService.payment_timeOut(id);
        return s;
    }
    @GetMapping("/payment/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String s = paymentService.paymentCircuitBreaker(id);
        return s;
    }

}
