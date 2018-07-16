package com.ooa1769.bs.book.support;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class SearchResult {

    private List<Document> documents;
    private Meta meta;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Document {
        private String title;
        private String contents;
        private String url;
        private String isbn;
        private ZonedDateTime datetime;
        private List<String> authors;
        private String publisher;
        private List<String> translators;
        private Integer price;
        @JsonProperty("sale_price")
        private Integer salePrice;
        @JsonProperty("sale_yn")
        private String saleYn;
        private String category;
        private String thumbnail;
        private String barcode;
        @JsonProperty("ebook_barcode")
        private String ebookBarcode;
        private String status;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Meta {
        @JsonProperty("is_end")
        private boolean end;
        @JsonProperty("pageable_count")
        private Integer pageableCount;
        @JsonProperty("total_count")
        private Integer totalCount;
    }
}
