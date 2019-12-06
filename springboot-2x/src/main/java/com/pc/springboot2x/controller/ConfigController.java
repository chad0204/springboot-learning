package com.pc.springboot2x.controller;

import com.pc.springboot2x.config.ConfigBean;
import com.pc.springboot2x.config.MyConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试各种属性注入
 *
 * @author pengchao
 * @since 17:28 2019-11-19
 */
@RestController
//@EnableConfigurationProperties({MyConfigBean.class})
public class ConfigController {

    @Autowired
    ConfigBean configBean;

    @Autowired
    private MyConfigBean myConfigBean;

    @RequestMapping("/config")
    public String index(){

        return configBean.toString()+"::"+myConfigBean.toString();
    }

}
