package com.practice.redispractice.service;

import com.practice.redispractice.exception.MemberNotFoundException;
import com.practice.redispractice.model.Member;
import com.practice.redispractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버 조회 실패, 해당하는 멤버가 없습니다."));
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

}