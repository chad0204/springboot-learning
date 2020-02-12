package com.pc.servicehystrix.config;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

/**
 * 使用继承的方式实现服务调用
 *
 * 该实例只能调用一次
 *
 * @author dongxie
 * @date 10:09 2020-02-11
 */
public class UserCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(RestTemplate restTemplate, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("getUserById"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withCircuitBreakerRequestVolumeThreshold(10)//至少有10个请求，熔断器才进行错误率的计算
                    .withCircuitBreakerSleepWindowInMilliseconds(5000)//熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                    .withCircuitBreakerErrorThresholdPercentage(50)//错误率达到50开启熔断保护
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                    .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)//最大并发请求量
                ).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("userPool"))
        );
        this.id = id;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://eureka-client/getUser?id="+id,String.class);
    }

    /**
     *  异常回调
     */
    @Override
    protected String getFallback() {
        Throwable executionException = getExecutionException();
        System.out.println(executionException.fillInStackTrace());
//        executionException.printStackTrace();
        return "faild";
    }

    /**
     * 开启请求缓存功能，如果是相同的参数，则直接获取缓存而不是重新发起请求。
     * 请求缓存在run()和construct()执行之前生效，避免线程开销。
     *
     */
//    @Override
//    protected String getCacheKey() {
//        return String.valueOf(id);
//    }

    /**
     *  清除缓存
     */
//    private static void flushCache(Long id) {
//        //刷新缓存，根据id进行清理
//        HystrixRequestCache.getInstance(commandKey, HystrixConcurrencyStrategyDefault.getInstance())
//                .clear(String.valueOf(id));
//
//    }
}
