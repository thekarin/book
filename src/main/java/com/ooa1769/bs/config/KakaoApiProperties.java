package com.ooa1769.bs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kakao.api")
public class KakaoApiProperties {

    private final static String DEFAULT_URL = "https://dapi.kakao.com/v2/search/book";
    private final static String KAKAO_AUTHORIZATION_HEADER = "KakaoAK";

    private String key;
    private String url = DEFAULT_URL;

    public String authorizationHeaderValue() {
        Assert.notNull(key, "api key not null");
        return String.format("%s %s",KAKAO_AUTHORIZATION_HEADER, key);
    }

    public String getUrl(String queryParam) {
        Assert.notNull(queryParam, "path not null");
        return String.format("%s?%s", url, queryParam);
    }
}