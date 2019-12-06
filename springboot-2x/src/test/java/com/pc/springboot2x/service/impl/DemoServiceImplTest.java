package com.pc.springboot2x.service.impl;

import com.pc.springboot2x.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * TODO
 *
 * @author pengchao
 * @since 15:30 2019-11-19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoServiceImplTest {

    @Autowired
    DemoService demoService;

    @Test
    public void getUser() {
        demoService.getUser();


        System.out.println();
    }
}