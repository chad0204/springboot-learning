package com.pc.springboot2x.controller;

import com.pc.springbootstarterhello.stat.HelloServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用自定义的starter
 *
 * @author dongxie
 * @date 19:37 2019-11-21
 */
@RestController
public class StarterController {

    @Autowired
    private HelloServiceConfiguration helloService;


    @RequestMapping("/name")
    public String getName() {
        return helloService.getName();
    }

    @RequestMapping("/hobby")
    public String getHobby() {
        return helloService.getHobby();
    }
}
