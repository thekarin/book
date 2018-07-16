package com.ooa1769.bs.member.support;

import com.ooa1769.bs.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.email = ?1")
    Optional<Member> findByEmail(String email);
}
