package com.ooa1769.bs.web;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class PagingInfo {

    private int displayPageNum = 10;
    private Page result;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private List<Pageable> pageList;

    public PagingInfo(Page result) {
        this.result = result;
        this.pageList = new ArrayList<>();
        calcPages();
    }

    private void calcPages() {
        int curruentPage = result.getNumber() + 1;
        endPage = (int) (Math.ceil(curruentPage / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;
        log.debug("currentPage : {}, endPage : {}", curruentPage, endPage);
        log.debug("result.getTotalElements() : {}, {}", result.getTotalElements(), result.getSize());

        int tempEndPage = (int) Math.ceil(result.getTotalElements() / (double)result.getSize());

        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        log.debug("startPage : {}, endPage : {}, tempEndpage : {}", startPage, endPage, tempEndPage);
        for(int i = startPage ; i <= endPage; i++){
            PageRequest pageRequest = new PageRequest(i - 1, result.getSize());
            pageList.add(pageRequest);
        }

        prev = startPage == 1 ? false : true;
        next = endPage * result.getSize() >= result.getTotalElements() ? false : true;
    }
}
