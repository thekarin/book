package com.ooa1769.bs.member;

import com.ooa1769.bs.support.domain.UrlGeneratable;
import com.ooa1769.bs.support.jpa.AbstractEntity;
import com.ooa1769.bs.support.jpa.BooleanToYNConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

@Slf4j
@Entity
@Table(name = "member", indexes = {@Index(name = "idx_member_email",  columnList="email", unique = true)})
@Convert(converter = BooleanToYNConverter.class, attributeName = "enabled")
public class Member extends AbstractEntity implements UrlGeneratable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Getter
    @Column(length = 60, nullable = false)
    private String email;

    @Getter
    @Column(length = 20, nullable = false)
    private String name;

    @Getter
    @Column(length = 60, nullable = false)
    private String password;

    @Getter
    @Column
    private boolean enabled = true;

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(
            name = "search_history",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @org.hibernate.annotations.OrderBy(clause = "searchDate desc")
    private Set<SearchHistory> searchHistories;

    protected Member() {}

    public Member(String email, String name, String password, boolean enabled) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

    public Member(Long id, String email, String name, String password, boolean enabled, Set<SearchHistory> searchHistories) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.searchHistories = searchHistories;
    }

    public void addSearchHistory(SearchHistory searchHistory) {
        if (searchHistories.contains(searchHistory)) {
            searchHistories.remove(searchHistory);
        }
        searchHistories.add(searchHistory);
    }

    public void removeHistory(SearchHistory searchHistory) {
        searchHistories.remove(searchHistory);
    }

    public SearchHistory findByKeyword(String searchKeyword) {
        return findByKeyword(new SearchHistory(searchKeyword));
    }

    public SearchHistory findByKeyword(SearchHistory searchHistory) {
        return searchHistories.stream()
                .filter(s -> s.equals(searchHistory))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String generateUrl() {
        return null;
    }

    @Override
    public String generateRestUrl() {
        return String.format("/api/members/%d", id);
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", enabled=" + enabled +
                ", searchHistories=" + searchHistories + "]";
    }
}
