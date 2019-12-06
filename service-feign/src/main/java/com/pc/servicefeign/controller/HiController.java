package com.pc.servicefeign.controller;

import com.pc.servicefeign.clients.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dongxie
 * @date 17:15 2019-11-28
 */
@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;//报错忽视，schedualServiceHi不是显示注入到ioc的

    /**
     * 本质区别就是不需要restTemplate或者httpClient，根据路径来访问其他服务
     */
    @GetMapping(value = "/hello")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHelloFromClient( "feign-"+name );
    }

}
