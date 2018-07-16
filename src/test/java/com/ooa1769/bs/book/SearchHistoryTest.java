package com.ooa1769.bs.book;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.SearchHistory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchHistoryTest {

    private Member member;
    private LocalDateTime olderDate = LocalDateTime.of(2018, 7,11, 23, 23, 50);
    private LocalDateTime newerDate = LocalDateTime.of(2018, 7,11, 23, 30, 51);
    private SearchHistory sameKeywordOlderHistory = new SearchHistory("스프링", olderDate);
    private SearchHistory sameKeyWordNewerHistory = new SearchHistory("스프링", newerDate);
    private SearchHistory otherKeywordHistory = new SearchHistory("스프링부트", LocalDateTime.of(2018, 7,12, 23, 23, 50));

    @Before
    public void setup() {
        Set<SearchHistory> searchHistories = new HashSet<>();
        searchHistories.add(sameKeywordOlderHistory);
        member = new Member(1L, "ooa1769@naver.com", "김남열", "1234", true, searchHistories);
    }

    @Test
    public void 검색_목록_추가_삭제() {
        Set<SearchHistory> histories = member.getSearchHistories();
        assertThat(histories).contains(sameKeywordOlderHistory);
        assertThat(histories).size().isEqualTo(1);

        member.addSearchHistory(otherKeywordHistory);
        assertThat(histories).contains(otherKeywordHistory);
        assertThat(histories).size().isEqualTo(2);

        member.removeHistory(sameKeywordOlderHistory);
        assertThat(histories).doesNotContain(sameKeywordOlderHistory);
        assertThat(histories).size().isEqualTo(1);
    }

    @Test
    public void 동일한_키워드_검색시_날짜갱신() {
        Set<SearchHistory> histories = member.getSearchHistories();
        assertThat(histories).size().isEqualTo(1);

        SearchHistory history = member.findByKeyword("스프링");
        assertThat(history.getSearchDate()).isEqualTo(olderDate);
        assertThat(history.getSearchKeyword()).isEqualTo("스프링");

        member.addSearchHistory(sameKeyWordNewerHistory);

        history = member.findByKeyword("스프링");
        assertThat(histories).size().isEqualTo(1);
        assertThat(history.getSearchDate()).isEqualTo(newerDate);
        assertThat(history.getSearchKeyword()).isEqualTo("스프링");
    }
}
