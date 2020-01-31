package com.pc.code;

import com.netflix.loadbalancer.Server;
import java.util.List;

public interface ILoadBalancer {

    //像负载均衡维护的服务列表中增加实例
    void addServers(List<Server> newServers);

    //使用某种策略从列表中获取实例
    Server chooseServer(Object key);

    //
    void markServerDown(Server server);

    //获取所有存活的实例
    List<Server> getReachableServers();

    //获取所有实例
    List<Server> getAllServers();
}
