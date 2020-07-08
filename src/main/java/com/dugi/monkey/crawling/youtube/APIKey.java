package com.dugi.monkey.crawling.youtube;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class APIKey {

    @Value(value = "${api_youtube}")
    private String youtube;
}