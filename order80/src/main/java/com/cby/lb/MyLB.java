package com.cby.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public class MyLB implements LoadBalancer
{
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceIntance) {
        return null;
    }

}
