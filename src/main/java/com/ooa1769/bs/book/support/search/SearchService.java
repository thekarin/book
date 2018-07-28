package com.ooa1769.bs.book.support.search;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.web.dto.BookSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    private Map<ApiType, BookSearchClient> searchers = new HashMap<>();

    @Autowired
    public SearchService(@Qualifier("kakao") BookSearchClient bookSearchClient) {
        searchers.put(ApiType.KAKAO, bookSearchClient);
    }

    public Page<Book> search(ApiType apiType, BookSearchParam bookSearchParam) {
        return searchers.get(apiType).search(bookSearchParam.pageable(), bookSearchParam.queryParam());
    }
}
