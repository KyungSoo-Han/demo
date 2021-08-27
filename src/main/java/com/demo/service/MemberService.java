package com.demo.service;

import com.demo.domain.Member;
import com.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepositroy;

    @Transactional
    public void save(Member member) {

        List<Member> memberList = memberRepositroy.findByEmail(member.getEmail());
        memberAlreadyJoinChecked(memberList);

        memberRepositroy.save(member);
    }

    private void memberAlreadyJoinChecked(List<Member> memberList) {
        if (!memberList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

}
