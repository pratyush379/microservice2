package com.pratyush.currencyconversionservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name ="currency-exchange" , url = "localhost:8000")//name from application.prop //problem : here we are hardcoding the uri : soln : naming server
//automatically discover instances?how? --> service registry or naming server
//Soln->
//Load Balancing
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from ,
            @PathVariable String to

    );
}
