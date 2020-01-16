package com.pc.serviceribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者，测试访问多个实例时的负载均衡
 *
 * @author dongxie
 * @date 11:00 2019-11-27
 */
@RestController
public class HelloController {

    /**
     *  使用开启了负载均衡功能的restTemplate访问服务实例
     */
    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public String hello(String name) {
        name = "ribbon-"+ name;
        return restTemplate.getForObject("http://eureka-client//hello?name="+name,String.class);
    }

    /**
     * 使用负载均衡器,从eureka获取实例(当然也可以不通过eureka，而是从配置中读取服务实例进行负载均衡)
     */
    @Autowired
    LoadBalancerClient loadBalancer;

    @GetMapping("/loadBalance")
    public String loadBalance() {
        //获取服务实例
        ServiceInstance serviceInstance = loadBalancer.choose("eureka-client");
        return serviceInstance.getHost()+":"+serviceInstance.getPort();
    }




}
