package com.pc.gatewaylimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;
import java.util.Objects;

/**
 *
 * 计数器算法：
 *      1.原理,限制一段时间能通过的请求数，如qps=100/s，每处理一个请求，计数加1，加到100，拒绝请求，等到1秒后计数归零。
 *      2.实现，使用AtomicLong#incrementAndGet()
 *      3.缺陷，突刺现象，比如在前1ms内就突发100个请求，那么后面999ms都会拒绝请求，但服务器却是闲置的。
 *
 * 漏桶算法：
 *      1.原理，内部有一个请求容器，不管调用方请求的速率如何，服务方都按照固定的速率进行处理，未处理的请求就放入桶中，如果桶满就拒绝请求。
 *      2.实现，队列加线程池，队列用来存放请求，线程池从队列中获取请求进行处理。
 *      3.缺陷，虽然解决了突刺现象，但无法应对短时大量请求，会因为瞬间塞满桶而拒绝请求。
 *
 * 令牌桶算法：
 *      1.原理，内部有一个令牌容器，算法以固定的速率向桶中放入令牌，每个请求需要先获取令牌才能执行，否则等待或拒绝。因为会持续向桶中放入令牌，
 *             所以可能出现桶中令牌满的情况，这时就可以应对短时大量请求，只有当桶中令牌耗尽才会拒绝或等待请求。
 *             令牌桶算法中，容量是c，放入令牌的速度是v，那么能扛住的最大请求数是c,长期来看请求处理的速度为v.
 *      2.实现，一个队列存放令牌，一个线程池向队列中放入令牌，每来一个请求就获取一个令牌执行。gateway使用redis+lua的方式实现令牌桶。
 *      3.缺陷，既不会因为请求上限闲置服务器，也不会因为桶容量固定而拒绝突发请求。令牌桶算法能够在限制调用的平均速率的同时还允许一定程度的突发调用。
 *
 *
 */

@SpringBootApplication
public class GatewayLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayLimiterApplication.class, args);
    }

    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }

    @Bean
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("user")));
    }


}
