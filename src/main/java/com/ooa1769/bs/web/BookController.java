package com.ooa1769.bs.web;

import com.ooa1769.bs.book.Book;
import com.ooa1769.bs.book.BookMark;
import com.ooa1769.bs.book.SearchOption;
import com.ooa1769.bs.book.SearchTarget;
import com.ooa1769.bs.book.support.BookService;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberService;
import com.ooa1769.bs.support.security.LoginMember;
import com.ooa1769.bs.support.util.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(Mappings.BOOK)
public class BookController {

    private final BookService bookService;
    private final MemberService memberService;

    @Autowired
    public BookController(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@LoginMember Member member, @ModelAttribute("searchOption") SearchOption searchOption, Model model) {
        if (!ObjectUtils.isEmpty(member) && !ObjectUtils.isEmpty(searchOption.getQuery())) {
            memberService.addSearchHistory(member, searchOption.getQuery());
        }

        Page<Book> pageBook = bookService.getBooksByKeyword(searchOption);
        model.addAttribute("books", pageBook.getContent());
        model.addAttribute("targets", SearchTarget.values());
        model.addAttribute("pagingInfo", new PagingInfo(pageBook));
        return "book/index";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@ModelAttribute("searchOption") SearchOption searchOption,
                       @RequestParam(defaultValue = "") String type,
                       @RequestParam(defaultValue = "") String keyword,
                       Model model) {
        model.addAttribute("book", bookService.getBookByIsbn(searchOption));
        model.addAttribute("searchOption", searchOption);
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        return "book/view";
    }

    // ============== BookMark ============
    @RequestMapping(value = Mappings.BOOKMARK, method = RequestMethod.GET)
    public String bookmarkList(@LoginMember Member member,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model) {
        PageRequest pageRequest = new PageRequest(page - 1, size, Sort.Direction.ASC, "title");
        Page<BookMark> pageBookMark = bookService.getBookMarksByMember(member, pageRequest);
        model.addAttribute("searchOption", new SearchOption(page, size));
        model.addAttribute("bookmarks", pageBookMark.getContent());
        model.addAttribute("pagingInfo", new PagingInfo(pageBookMark));
        return "book/bookmark";
    }
}
