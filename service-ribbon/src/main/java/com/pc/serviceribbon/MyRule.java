package com.pc.serviceribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author dongxie
 * @date 16:06 2019-11-28
 */
public class MyRule {

    //配置负载均衡策略，如果在启动类或者配置文件中指定被负载的服务名称，那么策略就不是全局而是针对特定服务
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
//        return new RoundRobinRule();
    }
}
