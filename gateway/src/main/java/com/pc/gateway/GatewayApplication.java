package com.pc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@SpringBootApplication
@RestController
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * 使用RouteLocatorBuilder的bean去创建路由
     *
     * 除了创建路由RouteLocatorBuilder可以让你添加各种predicates和filters
     *
     * 下面的代码：
     *      1.让请求"/get"都转发到"http://httpbin.org:80"
     *      2.在route配置上，我们添加了一个filter，该filter会将请求添加一个header,key为hello，value为world。
     *      3.另外一个router，该router使用host去断言请求是否进入该路由，当请求的host有“*.hystrix.com”，都会进入该router，
     *        该router中有一个hystrix的filter,该filter可以配置名称、和指向性fallback的逻辑的地址，比如本案例中重定向到了“/fallback”
     *
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String httpUri = "http://httpbin.org:80";
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                .route(p -> p
                        .host("*.hystrix.com")
                        .filters(f -> f
                                .hystrix(config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(httpUri))
                .build();
    }

    /**
     * 终端运行：curl --dump-header - --header 'Host: www.hystrix.com' http://localhost:8080/delay/3
     * 返回：fallback
     */
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

    //浏览器返回结果
//    {
//        "args": {},
//        "headers": {
//                "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
//                "Accept-Encoding": "gzip, deflate, br",
//                "Accept-Language": "zh-CN,zh;q=0.9",
//                "Forwarded": "proto=http;host=\"localhost:8080\";for=\"0:0:0:0:0:0:0:1:60712\"",
//                "Hello": "World",
//                "Host": "httpbin.org",
//                "Sec-Fetch-Mode": "navigate",
//                "Sec-Fetch-Site": "none",
//                "Upgrade-Insecure-Requests": "1",
//                "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36",
//                "X-Forwarded-Host": "localhost:8080"
//        },
//        "origin": "0:0:0:0:0:0:0:1, 60.190.251.242, ::1",
//            "url": "https://localhost:8080/get"
//    }



}
