package com.pc.servicehystrix.service;

import com.pc.servicehystrix.config.UserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 不使用注解，而直接使用命令来执行
 *
 * @author dongxie
 * @date 18:22 2020-02-12
 */
@Service
public class CommandService {

    @Autowired
    private RestTemplate restTemplate;


    public String getUser(Long id) {
        UserCommand helloCommand = new UserCommand(restTemplate,id);
        return helloCommand.execute();
    }

}
