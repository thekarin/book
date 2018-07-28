package com.ooa1769.bs.book.support.search.kakao;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class KakaoApiPropertiesTest {

    KakaoApiProperties properties;

    @Before
    public void setUp() throws Exception {
        properties = new KakaoApiProperties();
    }

    @Test
    public void 디폴트_URL_속성값() {
        assertEquals("https://dapi.kakao.com/v2/search/book", properties.getUrl());
        assertNull(properties.getKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void 인증_헤더_값_검증_api키값_없는경우() {
        properties.authorizationHeaderValue();
    }

    @Test
    public void 인증_헤더_값_검증() {
        properties.setKey("1abc24343adcf43asd6keka42");
        assertEquals("KakaoAK 1abc24343adcf43asd6keka42", properties.authorizationHeaderValue());
    }

    @Test
    public void 카카오API_호출할_쿼리문자열_검증() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("size", "10");
        params.put("query", "spring");
        params.put("target", "title");
        params.put("category", "1");
        params.put("sort", "title");
        assertEquals("https://dapi.kakao.com/v2/search/book?size=10&query=spring&page=1&sort=title&category=1&target=title", properties.requestUrl(params));
    }
}