package com.ooa1769.bs.book;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.support.domain.UrlGeneratable;
import com.ooa1769.bs.support.jpa.AbstractEntity;
import com.ooa1769.bs.support.util.Mappings;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "bookmark")
public class BookMark extends AbstractEntity implements UrlGeneratable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    @Getter
    private Long id;

    @Getter
    private String isbn;

    @Getter
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public BookMark() {
    }

    public BookMark(String isbn, String title, Member member) {
        this.isbn = isbn;
        this.title = title;
        this.member = member;
    }

    @Override
    public String generateUrl() {
        return null;
    }

    @Override
    public String generateRestUrl() {
        return String.format(Mappings.BOOKMARKS_CREATE_FORMAT, id);
    }

    @Override
    public String toString() {
        return "BookMark [id=" + id + ", isbn=" + isbn + ", title=" + title + "]";
    }
}
