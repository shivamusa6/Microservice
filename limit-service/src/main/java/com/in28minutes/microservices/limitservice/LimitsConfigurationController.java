package com.in28minutes.microservices.limitservice;

import com.in28minutes.microservices.limitservice.bean.LimitsConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHystrix
public class LimitsConfigurationController {
    @Autowired
    Configuration configuration;

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsForConfiguration(){
        return new LimitsConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }
    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
    public LimitsConfiguration retrieveConfiguration() {
        throw new RuntimeException("Not available");
    }

    public LimitsConfiguration fallbackRetrieveConfiguration() {
        return new LimitsConfiguration(999, 9);
    }

}
