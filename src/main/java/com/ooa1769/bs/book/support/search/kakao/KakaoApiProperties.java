package com.ooa1769.bs.book.support.search.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

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

    public String requestUrl(Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(URI.create(url));
        for (Map.Entry<String, String> param : params.entrySet()) {
            builder.queryParam(param.getKey(), param.getValue());
        }
        return builder.build().toUriString();
    }
}