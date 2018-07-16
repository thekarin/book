package com.ooa1769.bs.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Objects;

@Embeddable
public class SearchHistory {

    @Getter
    @Column(nullable = false)
    private String searchKeyword;

    @Getter
    @Column(nullable = false)
    private LocalDateTime searchDate;

    protected SearchHistory() {}

    public SearchHistory(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        this.searchDate = LocalDateTime.now();
    }

    public SearchHistory(String searchKeyword, LocalDateTime searchDate) {
        this.searchKeyword = searchKeyword;
        this.searchDate = searchDate;
    }

    @JsonIgnore
    public String getFormattedSearchDate() {
        return getFormattedDate(searchDate, "yyyy.MM.dd HH:mm:ss");
    }

    private String getFormattedDate(LocalDateTime dateTime, String format) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchHistory)) return false;
        SearchHistory that = (SearchHistory) o;
        return Objects.equals(searchKeyword, that.searchKeyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchKeyword);
    }

    @Override
    public String toString() {
        return "SearchHistory [searchKeyword=" + searchKeyword + ", searchDate=" + searchDate + "]";
    }
}
