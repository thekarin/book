package com.ooa1769.bs.web.dto;

import com.ooa1769.bs.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private String email;

    private String password;

    private String name;

    public Member toMember() {
        return new Member(email, name, password, true);
    }

    @Override
    public String toString() {
        return "MemberDto [email=" + email + ", name=" + name + ", password=" + password + "]";
    }
}
