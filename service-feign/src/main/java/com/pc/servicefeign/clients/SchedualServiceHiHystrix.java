package com.pc.servicefeign.clients;

import org.springframework.stereotype.Component;

/**
 *
 * 实现类提供回调方法
 *
 * @author dongxie
 * @date 14:28 2019-11-29
 */
@Component
public class SchedualServiceHiHystrix implements SchedualServiceHi {
    @Override
    public String sayHelloFromClient(String name) {
        return "sorry!!!!!!!!!!"+name;
    }
}
