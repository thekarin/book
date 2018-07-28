package com.ooa1769.bs.book.support.search;

import com.ooa1769.bs.book.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BookSearchClient {

    Page<Book> search(Pageable pageable, Map<String, String> queryParam);
}
