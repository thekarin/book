package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.Book;
import com.ooa1769.bs.book.SearchOption;
import com.ooa1769.bs.config.KakaoApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchService {

    private final RestTemplate restTemplate;
    private final KakaoApiProperties properties;

    @Autowired
    public SearchService(RestTemplate restTemplate, KakaoApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public Page<Book> search(SearchOption searchOption) {
        if (StringUtils.isEmpty(searchOption.getQuery())) {
            return new PageImpl<>(Collections.emptyList());
        }

        Optional<SearchResult> resultOpt = execute(searchOption);

        if (resultOpt.isPresent()) {
            SearchResult searchResult = resultOpt.get();
            List<Book> books = searchResult.getDocuments().stream()
                    .map(this::convertBook)
                    .collect(Collectors.toList());

            return new PageImpl<>(books,
                    new PageRequest(searchOption.getPage() - 1, searchOption.getSize()),
                    searchResult.getMeta().getPageableCount());
        }

        return new PageImpl<>(Collections.emptyList());
    }

    private Optional<SearchResult> execute(SearchOption searchOption) {
        ResponseEntity<SearchResult> responseEntity = null;

        String url = properties.getUrl(searchOption.queryParam());
        try {
            responseEntity = restTemplate.exchange(url,
                    HttpMethod.GET,
                    getHttpHeaders(),
                    SearchResult.class);
        } catch (RestClientException e) {
            log.error("RestClientException {}", e.getMessage());
        }

        return Optional.ofNullable(responseEntity.getBody());
    }

    private HttpEntity<?> getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", properties.authorizationHeaderValue());
        return new HttpEntity<>(headers);
    }

    private Book convertBook(SearchResult.Document document) {
        return Book.builder()
                .title(document.getTitle())
                .contents(document.getContents())
                .url(document.getUrl())
                .isbn(document.getIsbn())
                .datetime(document.getDatetime())
                .authors(document.getAuthors())
                .publisher(document.getPublisher())
                .translators(document.getTranslators())
                .price(document.getPrice())
                .salePrice(document.getSalePrice())
                .saleYn(document.getSaleYn())
                .category(document.getCategory())
                .thumbnail(document.getThumbnail())
                .barcode(document.getBarcode())
                .ebookBarcode(document.getEbookBarcode())
                .status(document.getStatus())
                .build();
    }

}
