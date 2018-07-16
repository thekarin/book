package com.ooa1769.bs.member.support;

import com.ooa1769.bs.member.SearchHistory;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.web.dto.MemberDto;
import com.ooa1769.bs.member.MemberAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member register(MemberDto memberDto) {
        if (emailExist(memberDto.getEmail())) {
            throw new MemberAlreadyExistException("이미 등록된 id입니다. " + memberDto.getEmail());
        }
        String encodePassword = passwordEncoder.encode(memberDto.getPassword());
        log.debug("encodePassword = {}", encodePassword);
        memberDto.setPassword(encodePassword);
        return memberRepository.save(memberDto.toMember());
    }

    @Override
    public void addSearchHistory(Member member, String searchKeyword) {
        member.addSearchHistory(new SearchHistory(searchKeyword));
        memberRepository.save(member);
    }

    private boolean emailExist(final String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}
