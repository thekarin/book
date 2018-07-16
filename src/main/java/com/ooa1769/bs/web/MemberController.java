package com.ooa1769.bs.web;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberService;
import com.ooa1769.bs.support.security.LoginMember;
import com.ooa1769.bs.support.util.Mappings;
import com.ooa1769.bs.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(Mappings.MEMBER)
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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

    @RequestMapping("/history")
    public String searchHistory(@LoginMember Member member, Model model) {
        model.addAttribute("histories", member.getSearchHistories());
        return "member/searchHistory";
    }
}
