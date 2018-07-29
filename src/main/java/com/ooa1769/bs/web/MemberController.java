package com.ooa1769.bs.web;

import com.ooa1769.bs.book.support.SearchHistoryService;
import com.ooa1769.bs.member.support.MemberService;
import com.ooa1769.bs.support.util.Mappings;
import com.ooa1769.bs.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Mappings.MEMBER)
public class MemberController {

    private final MemberService memberService;
    private final SearchHistoryService searchHistoryService;

    @Autowired
    public MemberController(MemberService memberService, SearchHistoryService searchHistoryService) {
        this.memberService = memberService;
        this.searchHistoryService = searchHistoryService;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String registerPage() {
        return "member/registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(MemberDto memberDto) {
        memberService.register(memberDto);
        return "redirect:/member/congratulation";
    }

    @RequestMapping(value = "/congratulation", method = RequestMethod.GET)
    public String congratulation() {
        return "member/congratulation";
    }
}
