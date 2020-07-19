package com.dugi.monkey.crawling.youtube;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : 이병덕
 * @description : youtube api key 매핑 클래스
 * @date : 2020.07.19 22:02:31
 */

@Getter
@Component
public class APIKey {

    @Value(value = "${api_youtube}")
    private String youtube;
}