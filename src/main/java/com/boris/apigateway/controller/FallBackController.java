package com.boris.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/order_service_fallback")
    public String orderFallback(){
        return "Service is Down";
    }

    @GetMapping("/product_service_fallback")
    public String productFallback(){
        return "Service is Down";
    }

    @GetMapping("/payment_service_fallback")
    public String paymentFallback(){
        return "Service is Down";
    }
}
