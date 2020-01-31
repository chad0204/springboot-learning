package com.pc.serviceribbon;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dongxie
 * @date 16:06 2019-11-28
 */
@Configuration
public class Config {

    /**
     * 配置负载均衡策略，默认是全局策略。
     * 如果在启动类或者配置文件中指定被负载的服务名称，那么策略就不是全局而是针对特定服务。
     * java配置优先于配置文件
     *
     */
    @Bean
    public IRule ribbonRule() {
//        return new RandomRule();
//        return new RoundRobinRule();
        return new RetryRule(new WeightedResponseTimeRule());//默认是使用RoundRobinRule，也可构造器传入
    }


    /**
     * 注入的restTemplate开启负载均衡
     * LoadBalancerAutoConfiguration会给restTemplate配置拦截器
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
