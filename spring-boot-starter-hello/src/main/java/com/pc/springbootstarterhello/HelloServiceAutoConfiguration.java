package com.pc.springbootstarterhello;

import com.pc.springbootstarterhello.properties.HelloServiceProperties;
import com.pc.springbootstarterhello.stat.HelloServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 *
 * @author dongxie
 * @date 18:59 2019-11-21
 */
@Configuration//表明此类是一个配置类，将变为一个bean被spring进行管理
@EnableConfigurationProperties(HelloServiceProperties.class)//启用属性配置，将读取HelloServiceProperties里面的属性。
@ConditionalOnClass(HelloServiceConfiguration.class)//当类路径下面有HelloServiceConfiguration此类时，自动配置。
@ConditionalOnProperty(prefix = "com.pc", value = "enabled", matchIfMissing = true)//判断指定的属性是否具备指定的值。
public class HelloServiceAutoConfiguration {

    @Autowired
    private HelloServiceProperties helloServiceProperties;

    @Bean
    @ConditionalOnMissingBean(HelloServiceConfiguration.class)///当容器中没有指定bean是，创建此bean
    public HelloServiceConfiguration helloServiceConfiguration() {
        HelloServiceConfiguration helloService = new HelloServiceConfiguration();
        helloService.setName(helloServiceProperties.getName());
        helloService.setHobby(helloServiceProperties.getHobby());
        return helloService;
    }
}
