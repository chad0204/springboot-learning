package com.pc.eurekaclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@RefreshScope
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    //远程配置的属性
    @Value("${foo}")
    String foo;

    /**
     * 加载远程配置启动真的慢
     */
    @RequestMapping("/hello")
    public String hello(String name) {
        return "From-"+port+":hello,"+name+","+foo;
    }


    @RequestMapping("/hystrixHello")
    public String hystrixHello(String name) throws InterruptedException {
        //hystrix默认的超时时间是2000ms,这里通过睡眠1-3000来随机超时
        Thread.sleep(new Random().nextInt(3000));
        return "From-"+port+":hello,"+name+","+foo;
    }

}
