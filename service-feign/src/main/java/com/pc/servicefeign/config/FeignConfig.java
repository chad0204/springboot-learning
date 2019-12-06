package com.pc.servicefeign.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 配置类，覆盖FeignClientsConfiguration
 *
 * @author dongxie
 * @date 18:04 2019-11-28
 */
@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        //重试间隔为100ms，最大重试时间为1s,重试次数为5次
        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }

}
