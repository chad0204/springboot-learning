package com.pc.servicehystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.Future;

/**
 * 在ribbon中使用断路器
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 服务无法调用的熔断情况,同步方式
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://eureka-client/hello?name="+name,String.class);
    }

    /**
     * 注解方式-异步
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public Future<String> hiServiceV1(String name) {
        return  new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://eureka-client/hello?name="+name,String.class);
            }
        };
    }


    /**
     * 服务调用超时的熔断情况
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "timeOut",commandKey = "helloKey",threadPoolKey = "helloThreadKey")
    public String timeoutService(String name) {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://eureka-client/hystrixHello?name="+name,String.class);
        long end = System.currentTimeMillis();
        System.out.println(start-end);
        return result;
    }

    /**
     * 调用错误的回调方法
     * @param name
     * @return
     */
    public String hiError(String name,Throwable e) {
        return "hi,"+name+",sorry,error!";
    }

    /**
     * 调用超时的回调方法
     * @param name
     * @return
     */
    public String timeOut(String name) {
        return "hi,"+name+",sorry,timeout!";
    }






}
