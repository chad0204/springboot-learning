package com.pc.springbootstarterhello.stat;

/**
 * 配置类
 *
 * @author dongxie
 * @date 19:05 2019-11-21
 */
public class HelloServiceConfiguration {

    private String name;

    private String hobby;

    public String getName() {
        return "name is " + name;
    }

    public String getHobby() {
        return "hobby is " + hobby;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
