package com.pc.servicehystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 *
 * 本服务是在restTemplate+ribbon上开启熔断功能
 * 如果在feign上使用，不需要引入依赖和显示开启，只需要配置文件中配置即可feign.hystrix.enabled=true
 *
 *
 * 熔断的情况：服务断开，服务超时(默认2000ms)
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix//开启熔断功能
//@EnableHystrixDashboard//开启熔断监控
public class ServiceHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHystrixApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
