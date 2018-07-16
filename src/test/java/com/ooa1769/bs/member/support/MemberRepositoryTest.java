package com.ooa1769.bs.member.support;

import com.ooa1769.bs.member.SearchHistory;
import com.ooa1769.bs.member.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;
    private LocalDateTime olderDate = LocalDateTime.of(2018, 7,11, 23, 23, 50);
    private LocalDateTime newerDate = LocalDateTime.of(2018, 7,11, 23, 30, 50);
    private SearchHistory sameKeywordOlderHistory = new SearchHistory("스프링", olderDate);
    private SearchHistory sameKeyWordNewerHistory = new SearchHistory("스프링", newerDate);
    private SearchHistory otherKeywordHistory = new SearchHistory("스프링부트", LocalDateTime.of(2018, 7,12, 23, 23, 50));

    @Before
    public void setup() {
        Set<SearchHistory> searchHistories = new HashSet<>();
        searchHistories.add(sameKeywordOlderHistory);
        searchHistories.add(sameKeyWordNewerHistory);
        searchHistories.add(otherKeywordHistory);
        member = new Member("ggulmool@naver.com", "김남열", "1234", true);
    }


    @Test
    public void 회원_등록() {
        // given
        entityManager.persist(member);
        entityManager.flush();

        // when
        Member foundMember = memberRepository.findByEmail("ggulmool@naver.com").get();

        // then
        assertThat(foundMember.getName())
                .isEqualTo(member.getName());
    }
}
