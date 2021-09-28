package com.sunxu;

import com.sunxu.demo.ServiceAInterface;
import com.sunxu.demo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/10 18:51
 * 这个FeignClient里面的名字,就是你要调用的那个服务的名字
 * spring cloud直接将feign和ribbon整合在一起了
 * feign + ribbon + eureka都是整合在一起
 * feign发起请求的时候,都会交给ribbon做负载均衡
 *
 * 我只是说,我要访问ServiceA,但是人家里面ServiceA里面定义哪些接口哪些参数,就不需要管了
 * 这里不用把service的接口重新写一遍,直接复用别人jar中提供的就ok了
 *
 * 这里类似于dubbo @Reference注解类似
 */
@FeignClient(value = "ServiceA", fallbackFactory = ServiceAClient.ServiceAClientFallbackFactory.class)
public interface ServiceAClient extends ServiceAInterface {

    @Component
    class ServiceAClientFallbackFactory implements FallbackFactory<ServiceAClient> {

        @Override
        public ServiceAClient create(Throwable cause) {
            System.out.println("异常了...");
            return new ServiceAClient() {
                @Override
                public String sayHello(Long aLong, String s, Integer integer) {
                    System.out.println("sayHello方法的降级逻辑");
                    return null;
                }

                @Override
                public String createUser(User user) {
                    System.out.println("createUser方法的降级逻辑");
                    return null;
                }

                @Override
                public String updateUser(Long aLong, User user) {
                    System.out.println("updateUser方法的降级逻辑");
                    return null;
                }

                @Override
                public String deleteUser(Long aLong) {
                    System.out.println("deleteUser方法的降级逻辑");
                    return null;
                }

                @Override
                public User getById(Long aLong) {
                    System.out.println("getById方法的降级逻辑");
                    return null;
                }
            };
        }
    }
}

