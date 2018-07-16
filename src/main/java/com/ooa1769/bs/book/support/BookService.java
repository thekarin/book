package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.Book;
import com.ooa1769.bs.book.BookMark;
import com.ooa1769.bs.book.BookNotFoundException;
import com.ooa1769.bs.book.SearchOption;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.web.dto.BookMarkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final SearchService searchService;
    private final BookMarkRepository bookMarkRepository;

    @Autowired
    public BookService(SearchService searchService, BookMarkRepository bookMarkRepository) {
        this.searchService = searchService;
        this.bookMarkRepository = bookMarkRepository;
    }

    public Page<Book> getBooksByKeyword(SearchOption searchOption) {
        return searchService.search(searchOption);
    }

    public Book getBookByIsbn(SearchOption searchOption) {
        Page<Book> pageBook = searchService.search(searchOption);
        if (pageBook.getTotalElements() == 0) {
            throw new BookNotFoundException("해당 책이 존재하지 않습니다.");
        }
        return pageBook.getContent().get(0);
    }

    // ============== BookMark ============
    public BookMark addBookMark(BookMarkDto bookMarkDto, Member member) {
        BookMark bookMark = bookMarkDto.createBookMark(member);
        return bookMarkRepository.save(bookMark);
    }

    public void deleteBookMark(BookMark bookMark) {
        bookMarkRepository.delete(bookMark);
    }

    @Transactional(readOnly = true)
    public BookMark getBookMarkById(Long id) {
        return bookMarkRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당 북마크가 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public Page<BookMark> getBookMarksByMember(Member member, Pageable pageable) {
        return bookMarkRepository.findByMember(member, pageable);
    }

    @Transactional(readOnly = true)
    public boolean isAddedBookMarkByMemberAndIsbn(Member member, String isbn) {
        return bookMarkRepository.findByMemberAndIsbn(member, isbn).isPresent();
    }
}
