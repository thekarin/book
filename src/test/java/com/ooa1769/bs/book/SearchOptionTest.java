package com.ooa1769.bs.book;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchOptionTest {

    @Test
    public void 쿼리스트링_테스트() throws Exception {
        SearchOption searchOption = new SearchOption();
        searchOption.setQuery("스프링");
        searchOption.setCategory(33);
        assertEquals("page=1&size=10&query=스프링&target=all&category=33", searchOption.queryParam());

        searchOption.setTarget("title");
        assertEquals("page=1&size=10&query=스프링&target=title&category=33", searchOption.queryParam());
    }

    @Test
    public void 페이지_사이즈_값_설정_테스트() throws Exception {
        SearchOption searchOption = new SearchOption();
        assertPageSize(1, 10, searchOption);

        searchOption.setPage(5);
        searchOption.setSize(10);
        assertPageSize(5, 10, searchOption);

        searchOption.setPage(51);
        searchOption.setSize(51);
        assertPageSize(1, 10, searchOption);

        searchOption.setPage(0);
        searchOption.setSize(5);
        assertPageSize(1, 5, searchOption);

        searchOption.setPage(0);
        searchOption.setSize(0);
        assertPageSize(1, 10, searchOption);
    }

    private void assertPageSize(int page, int size, SearchOption searchOption) {
        assertEquals(page, searchOption.getPage());
        assertEquals(size, searchOption.getSize());
    }
}
