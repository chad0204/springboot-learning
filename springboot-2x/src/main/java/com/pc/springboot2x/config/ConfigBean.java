package com.pc.springboot2x.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author pengchao
 * @since 17:27 2019-11-19
 */
@ConfigurationProperties(prefix = "demo")
@Component
public class ConfigBean {

    private String name;
    private String desc;
    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
