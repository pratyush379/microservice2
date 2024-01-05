package com.pratyush.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {


    /*
    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder routeLocatorBuilder){
      // Function<PredicateSpec, Buildable<Route>> routeFunction
        //        = p -> p.path("/get")
          //      .filters(f->f.addRequestHeader("MyHeader","MyURI")
            //            .addRequestParameter("Param","MyValue"))

              //  .uri("http://httpbin.org:80");
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/get")
                        .filters(f->f.addRequestHeader("MyHeader","MyURI")
                                .addRequestParameter("Param","MyValue"))

                        .uri("http://httpbin.org:80"))
                .build();
    }
    */
    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder routeLocatorBuilder){

        return routeLocatorBuilder.routes()
                .route(p -> p.path("/get")
                        .filters(f->f.addRequestHeader("MyHeader","MyURI")
                                .addRequestParameter("Param","MyValue"))

                        .uri("http://httpbin.org:80"))
                .route(p-> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p-> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p-> p.path("/currency-conversion-feign/**") // if a req start with currency-conversion-feign
                        .uri("lb://currency-conversion"))//then talk to eureka find the location of this service and load balance
                .route(p-> p.path("/currency-conversion-new/**")// creating new url
                        .filters(f->
                                f.rewritePath(
                                        "/currency-conversion-new/(?<segment>.*)",
                                        "/currency-conversion-feign/${segment}"))//redirect it
                        .uri("lb://currency-conversion"))
                .build();
    }
}
