package com.ooa1769.bs.book.support.search.naver;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class NaverApiPropertiesTest {

    private NaverApiProperties apiProperties;

    @Before
    public void setUp() throws Exception {
        apiProperties = new NaverApiProperties();
        apiProperties.setUrl("https://openapi.naver.com/v1/search/book.json");
        apiProperties.setClientId("99uFekoH2IOnqitrXblg");
        apiProperties.setClientSecret("sAtwPDzE_0");
    }

    @Test
    public void 네이버_api_url_확인() {
        assertEquals("https://openapi.naver.com/v1/search/book.json", apiProperties.getUrl());
    }

    @Test
    public void 클라이언트_id_secret_값_검증() {
        assertThat(apiProperties.getClientId()).isEqualTo("99uFekoH2IOnqitrXblg");
        assertThat(apiProperties.getClientSecret()).isEqualTo("sAtwPDzE_0");
    }
}
