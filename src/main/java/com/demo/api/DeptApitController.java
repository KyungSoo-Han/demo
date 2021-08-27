package com.demo.api;

import com.demo.domain.Department;
import com.demo.domain.Member;
import com.demo.service.DeptService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class DeptApitController {

    private final DeptService deptService;

    @PostMapping("/api/v1/dept/create")
    public CreateDeptResponse createDeptResponse (@RequestBody CreateDeptRequest req) {

        Department department = Department.builder()
                .dept_nm(req.dept_nm)
                .remark(req.remark)
                .build();
        deptService.save(department);

        return new CreateDeptResponse(department);

    }

    @Data
    static class CreateDeptRequest{
        @NotEmpty
        private String dept_nm;
        private String remark;

    }

    @Data
    @AllArgsConstructor
    static class CreateDeptResponse <T>{

        private T data;
   }

}
