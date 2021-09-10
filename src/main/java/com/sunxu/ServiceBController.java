package com.sunxu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/8/26 23:52
 */
@RestController
public class ServiceBController {

    @Autowired
    private ServiceAClient serviceAClient;

    @RequestMapping(value = "/greeting/{id}", method = RequestMethod.GET)
    public String greeting(@PathVariable("id") Long id,
                           @RequestParam("name") String name,
                           @RequestParam("age") Integer age) {
        return serviceAClient.sayHello(id, name, age);
    }
}
