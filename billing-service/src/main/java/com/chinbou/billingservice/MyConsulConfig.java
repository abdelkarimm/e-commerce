package com.chinbou.billingservice;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token" )
@Data
// un bean ds lequel on injecte les proprietes de config
public class MyConsulConfig {
    private long accessTokenTimeout;
    private long refreshTokenTimeout;
}
