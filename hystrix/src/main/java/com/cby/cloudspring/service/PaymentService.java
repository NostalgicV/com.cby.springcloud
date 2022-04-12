package com.cby.cloudspring.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String payment_ok(Integer id){
        return Thread.currentThread().getName()+"payment_ok"+id+"\t"+"牛逼666";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String payment_timeOut(Integer id){
        try {
            TimeUnit.MICROSECONDS.sleep(1000);
        }catch (InterruptedException e){ e.printStackTrace();}
        return "线程池"+Thread.currentThread().getName()+"payment_timeOut"+id+"\t"+"okkkk!";
    }

    public String paymentInfo_TimeOutHandler( Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOutHandler,id:"+id+"\t"+"寄了";
    }


//    服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })


//    execution.isolation.thread.timeoutInMilliseconds
//    execution.isolation.thread.timeoutInMillisSeconds
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }


    public String paymentCircuitBreaker_fallback(){
        return Thread.currentThread().getName()+"寄了";

    }
}
