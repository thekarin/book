package com.ooa1769.bs.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PagingInfoTest {

    private List<Integer> pagingList1;
    private List<Integer> pagingList2;
    private List<Integer> pagingList3;

    @Before
    public void setUp() throws Exception {
        pagingList1 = new ArrayList<>();
        pagingList2 = new ArrayList<>();
        pagingList3 = new ArrayList<>();
        IntStream.range(1, 158).forEach(i -> pagingList1.add(i));
        IntStream.range(1, 245).forEach(i -> pagingList2.add(i));
        IntStream.range(1, 417).forEach(i -> pagingList3.add(i));
    }

    @Test
    public void 현재페이지_1_보여줄_페이징_사이즈_10() {
        int currentPage = 1, size = 10;
        Page<Integer> pageImpl1 = new PageImpl<>(pagingList1, new PageRequest(currentPage - 1, size), pagingList1.size());
        Page<Integer> pageImpl2 = new PageImpl<>(pagingList2, new PageRequest(currentPage - 1, size), pagingList2.size());
        Page<Integer> pageImpl3 = new PageImpl<>(pagingList3, new PageRequest(currentPage - 1, size), pagingList3.size());

        PagingInfo pagingInfo1 = new PagingInfo(pageImpl1);
        PagingInfo pagingInfo2 = new PagingInfo(pageImpl2);
        PagingInfo pagingInfo3 = new PagingInfo(pageImpl3);

        confirmPagingInfo(pagingInfo1, 1, 10, true, false, 10);
        confirmPagingInfo(pagingInfo2, 1, 10, true, false, 10);
        confirmPagingInfo(pagingInfo3, 1, 10, true, false, 10);
    }

    @Test
    public void 현재페이지_12_보여줄_페이징_사이즈_10() {
        int currentPage = 12, size = 10;
        Page<Integer> pageImpl1 = new PageImpl<>(pagingList1, new PageRequest(currentPage - 1, size), pagingList1.size());
        Page<Integer> pageImpl2 = new PageImpl<>(pagingList2, new PageRequest(currentPage - 1, size), pagingList2.size());
        Page<Integer> pageImpl3 = new PageImpl<>(pagingList3, new PageRequest(currentPage - 1, size), pagingList3.size());

        PagingInfo pagingInfo1 = new PagingInfo(pageImpl1);
        PagingInfo pagingInfo2 = new PagingInfo(pageImpl2);
        PagingInfo pagingInfo3 = new PagingInfo(pageImpl3);

        confirmPagingInfo(pagingInfo1, 11, 16, false, true, 6);
        confirmPagingInfo(pagingInfo2, 11, 20, true, true, 10);
        confirmPagingInfo(pagingInfo3, 11, 20, true, true, 10);
    }

    private void confirmPagingInfo(PagingInfo pagingInfo, int startPage, int endPage, boolean isNext, boolean isPrev, int pageSize) {
        assertThat(pagingInfo.getStartPage()).isEqualTo(startPage);
        assertThat(pagingInfo.getEndPage()).isEqualTo(endPage);
        assertThat(pagingInfo.isNext()).isEqualTo(isNext);
        assertThat(pagingInfo.isPrev()).isEqualTo(isPrev);
        assertThat(pagingInfo.getPageList().size()).isEqualTo(pageSize);
    }
}
