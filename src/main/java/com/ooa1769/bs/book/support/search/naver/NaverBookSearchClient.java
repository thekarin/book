package com.ooa1769.bs.book.support.search.naver;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.book.support.search.BookSearchClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public class NaverBookSearchClient implements BookSearchClient {

    @Override
    public Page<Book> search(Pageable pageable, Map<String, String> queryParam) {
        return null;
    }
}
