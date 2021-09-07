package com.demo.api;

import com.demo.domain.Department;
import com.demo.domain.Member;
import com.demo.domain.MemberActivited;
import com.demo.service.DeptService;
import com.demo.service.MemberService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final DeptService deptService;

    @GetMapping("/api/v1/members")
    public Result memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getMember_id(),m.getName(),m.getPassword(),
                        m.getDepartment(),m.getEmail(),m.getGender(),m.getEnter_dt(),m.getTel_no(),m.getHp_no(), m.getActivated()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @PostMapping("/api/v1/member/join")
    public CreateMemberResponse createMemberResponse(@RequestBody CreateMemberRequest req) {

        Department department = deptService.findOne(req.dept_id);
        System.out.println(department.getDept_id());
        Member member = Member.builder()
                .name(req.name)
                .password(req.password)
                .email(req.email)
                .enter_dt(req.enter_dt)
                .tel_no(req.tel_no)
                .hp_no(req.hp_no)
                .gender(req.gender)
                .department(department)
                .build();

        memberService.save(member);

        return new CreateMemberResponse(member);

    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{

        private Long member_id;
        private String name;
        private String password;
        private Department department;
        private String email;
        private String gender;
        private String enter_dt;
        private String tel_no;
        private String hp_no;
        private MemberActivited activated;
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
        private String password;
        private Long dept_id;
        private String email;
        private String gender;
        private String enter_dt;
        private String tel_no;
        private String hp_no;
        private Department department;
    }
    @Data
    @AllArgsConstructor
    static class CreateMemberResponse <T>{

        private T data;
    }

}
