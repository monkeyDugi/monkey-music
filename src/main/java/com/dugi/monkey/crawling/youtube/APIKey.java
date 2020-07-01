package com.dugi.monkey.crawling.youtube;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@PropertySource("classpath:application-apikey.properties")
@Component(value = "apikey")
public class APIKey {

    @Value("${api.youtube}")
    private String youtube;
}