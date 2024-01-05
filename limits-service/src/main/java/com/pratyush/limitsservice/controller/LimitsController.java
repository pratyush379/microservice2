package com.pratyush.limitsservice.controller;


import com.pratyush.limitsservice.bean.Limits;
import com.pratyush.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retriveLimits(){
//return new Limits(1,100);
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
