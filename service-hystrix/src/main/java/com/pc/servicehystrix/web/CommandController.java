package com.pc.servicehystrix.web;

import com.pc.servicehystrix.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;

/**
 * 使用命令对象
 *
 * @author dongxie
 * @date 21:46 2020-02-12
 */
@RestController
public class CommandController {

    @Autowired
    CommandService commandService;

    /**
     *  使用继承HystrixCommand实现熔断
     *  只能使用一次
     */
    @GetMapping(value = "/getUser")
    public String getUser(@RequestParam Long id) throws ExecutionException, InterruptedException {
        return commandService.getUser(id);
    }
}
