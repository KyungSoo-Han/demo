package com.demo.api;

import com.demo.domain.Department;
import com.demo.domain.Member;
import com.demo.service.DeptService;
import com.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final DeptService deptService;

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
