package com.pc.springboot2x.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义配置文件
 *
 * @author pengchao
 * @since 17:44 2019-11-19
 */
@Configuration
@ConfigurationProperties(prefix = "my")
@PropertySource(value="classpath:my.properties",encoding="UTF-8")
@Component
public class MyConfigBean {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyConfigBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
