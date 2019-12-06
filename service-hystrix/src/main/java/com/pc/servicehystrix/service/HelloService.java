package com.pc.servicehystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 在ribbon中使用断路器
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://eureka-client/hello?name="+name,String.class);
    }

    /**
     * 调用错误的回调方法
     * @param name
     * @return
     */
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
