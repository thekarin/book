package com.ooa1769.bs.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class KakaoApiPropertiesTest {

    @Test
    public void 디폴트_URL_속성값() {
        KakaoApiProperties properties = new KakaoApiProperties();
        assertEquals("https://dapi.kakao.com/v2/search/book", properties.getUrl());
        assertNull(properties.getKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void 인증_헤더_값_검증_api키값_없는경우() {
        KakaoApiProperties properties = new KakaoApiProperties();
        properties.authorizationHeaderValue();
    }

    @Test
    public void 인증_헤더_값_검증() {
        KakaoApiProperties properties = new KakaoApiProperties();
        properties.setKey("1abc24343adcf43asd6keka42");
        assertEquals("KakaoAK 1abc24343adcf43asd6keka42", properties.authorizationHeaderValue());
    }
}