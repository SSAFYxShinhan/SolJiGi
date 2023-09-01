package com.ssafy.soljigi.member.controller;

import com.ssafy.soljigi.member.dto.MemberJoinDto;
import com.ssafy.soljigi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class JoinController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinDto memberJoinDto) {
        Long savedMemberId = memberService.join(memberJoinDto);
        return ResponseEntity.ok().body(savedMemberId.toString());
    }
}
