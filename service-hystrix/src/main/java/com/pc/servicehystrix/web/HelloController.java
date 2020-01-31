package com.pc.servicehystrix.web;

import com.pc.servicehystrix.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在ribbon中使用断路器
 *
 *  代码和ribbon一样，添加了注解和回调方法
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        return helloService.hiService( "hystrix-"+name );
    }

    /**
     * 测试超时熔断
     * @param name
     * @return
     */
    @GetMapping(value = "/hystrixHello")
    public String hystrixHello(@RequestParam String name) {
        return helloService.timeoutService( "hystrix-"+name );
    }
}
