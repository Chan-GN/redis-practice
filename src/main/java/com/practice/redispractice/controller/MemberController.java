package com.practice.redispractice.controller;

import com.practice.redispractice.model.Member;
import com.practice.redispractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/{id}")
    @Cacheable(value = "member")
    public Member getMemberById(@PathVariable("id") Long id) {
        return memberService.getMemberById(id);
    }

}
