package com.chinbou.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope // surveiller la config de ce bean
public class CustomerConfigTestController {
   /* @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;*/
    @Value("${customer.params.x}")
    private String x;
    @Value("${customer.params.y}")
    private String y;
    @GetMapping("/params")
    public Map<String, String> params(){
        return Map.of("x",x,"y",y);
    }

}
