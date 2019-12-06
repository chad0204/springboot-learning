package com.pc.springboot2x.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 配置文件优先级
 *
 * @author pengchao
 * @since 15:16 2019-11-19
 */
@RestController
public class HelloController {

    //三种配置文件都可以读取，当出现冲突时，优先级properties>yaml>yml
    @Value("${active.properties}")
    private String pro;
    @Value("${active.yaml}")
    private String y1;
    @Value("${active.yml}")
    private String y2;



    @RequestMapping("/hello")
    public String index(){

        return "hello"+pro+y1+y2;
    }

}
