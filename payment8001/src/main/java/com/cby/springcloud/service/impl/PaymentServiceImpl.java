package com.cby.springcloud.service.impl;

import com.cby.springcloud.dao.PaymentDao;
import com.cby.springcloud.entities.Payment;
import com.cby.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl  implements PaymentService {
   @Resource
    public PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(@Param("id") Long id){
        return paymentDao.getPaymentById(id);
    }
}
