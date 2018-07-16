package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.BookMark;
import com.ooa1769.bs.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    Optional<BookMark> findById(Long id);
    Optional<BookMark> findByMemberAndIsbn(Member member, String isbn);
    Page<BookMark> findByMember(Member member, Pageable pageRequest);
}
