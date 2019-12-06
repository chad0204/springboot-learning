package com.pc.springboot2x.service.impl;

import com.pc.springboot2x.service.DemoService;
import org.springframework.stereotype.Service;

/**
 *
 * @author pengchao
 * @since 15:28 2019-11-19
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String getUser() {
        return "路飞";
    }
}
