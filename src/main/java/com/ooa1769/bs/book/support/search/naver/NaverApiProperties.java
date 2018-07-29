package com.ooa1769.bs.book.support.search.naver;

import com.ooa1769.bs.web.dto.BookSearchParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
public class NaverApiProperties {

    private final static String NAVER_AUTHORIZATION_HEADER_CLIENT_ID = "X-Naver-Client-Id";
    private final static String KAKAO_AUTHORIZATION_HEADER_CLIENT_SECRET = "X-Naver-Client-Secret";

    @Value("${naver.api.url}")
    private String url;

    @Value("${naver.api.detailUrl}")
    private String detailUrl;

    @Value("${naver.api.clientId}")
    private String clientId;

    @Value("${naver.api.clientSecret}")
    private String clientSecret;

    public String getHeaderClientId() {
        return NAVER_AUTHORIZATION_HEADER_CLIENT_ID;
    }

    public String getHeaderClientSecret() {
        return KAKAO_AUTHORIZATION_HEADER_CLIENT_SECRET;
    }

    public String baseUrl(BookSearchParam bookSearchParam) {
        String target = bookSearchParam.getTarget();
        return isAllSearchUrl(target) ? url : detailUrl;
    }

    public Map<String, String> queryParam(BookSearchParam bookSearchParam) {
        Map<String, String> params = new HashMap<>();

        int startPage = (bookSearchParam.getPage() - 1) * bookSearchParam.getSize() + 1;
        params.put("start",  startPage + "");
        params.put("display", bookSearchParam.getSize() + "");
        params.put("sort", bookSearchParam.getSort());

        if (isAllSearchUrl(bookSearchParam.getTarget())) {
            params.put("query", bookSearchParam.getQuery());
        } else {
            params.put(bookSearchParam.getTarget(), bookSearchParam.getQuery());
        }

        return params;
    }

    private boolean isAllSearchUrl(String target) {
        return target.equals("all");
    }
}
