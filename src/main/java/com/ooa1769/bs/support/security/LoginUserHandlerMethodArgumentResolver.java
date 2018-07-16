package com.ooa1769.bs.support.security;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Slf4j
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        /*
        if (authentication.getAuthorities().contains()) {
            LoginMember loginMember = parameter.getParameterAnnotation(LoginMember.class);
            if (loginMember.required()) {
                throw new UnAuthorizedException("로그인이 필요합니다.");
            }
        }*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        return memberOpt.orElseThrow(() -> new UnAuthorizedException("로그인이 필요합니다."));
    }
}
