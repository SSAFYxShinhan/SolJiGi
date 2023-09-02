package com.ssafy.soljigi.member.service;

import com.ssafy.soljigi.member.dto.MemberJoinDto;
import com.ssafy.soljigi.member.entity.Member;
import com.ssafy.soljigi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member findByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserName Not Found"));
    }

    public Long join(MemberJoinDto memberJoinDto){
        // DTO를 통해서 Member 가 중복되는지 검사
        // 없으면 member 저장

        return 1L;
    }

}
