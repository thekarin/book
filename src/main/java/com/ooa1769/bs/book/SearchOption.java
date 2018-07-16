package com.ooa1769.bs.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

public class SearchOption {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_MIN_PAGE = 1;
    private static final int DEFAULT_MAX_PAGE = 50;
    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_MIN_SIZE = 1;
    private static final int DEFAULT_MAX_SIZE = 50;

    @Getter
    private int page;

    @Getter
    private int size;

    @Getter @Setter
    private String query;

    @Getter @Setter
    private String target;

    @Getter @Setter
    private Integer category;

    public SearchOption() {
        this.page = DEFAULT_PAGE;
        this.size = DEFAULT_SIZE;
    }

    public SearchOption(int page, int size) {
        this.page = page;
        this.size = size;
    }

    @Builder
    public SearchOption(int page, int size, String query, String target, Integer category) {
        this.page = page;
        this.size = size;
        this.query = query;
        this.target = target;
        this.category = category;
    }

    public void setPage(int page) {
        this.page = page < DEFAULT_MIN_PAGE || page > DEFAULT_MAX_PAGE ? DEFAULT_PAGE : page;
    }

    public void setSize(int size) {
        this.size = size < DEFAULT_MIN_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public String queryParam() {
        String queryStringFormat = "&%s=%s";
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s=%s", "page", page))
                .append(String.format(queryStringFormat, "size", size));

        if (!ObjectUtils.isEmpty(query)) {
            builder.append(String.format(queryStringFormat, "query", query));
        }

        if (!ObjectUtils.isEmpty(target)) {
            builder.append(String.format(queryStringFormat, "target", target));
        } else {
            builder.append(String.format(queryStringFormat, "target", "all"));
        }

        if (!ObjectUtils.isEmpty(category)) {
            builder.append(String.format(queryStringFormat, "category", category));
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "SearchOption [page=" + page + ", size=" + size + ", query=" + query + ", target=" + target + ", category=" + category + "]";
    }
}
