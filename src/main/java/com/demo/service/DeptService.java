package com.demo.service;

import com.demo.domain.Department;
import com.demo.domain.Member;
import com.demo.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;

    @Transactional
    public void save(Department department) {
        deptRepository.save(department);
    }


    public Department findOne(Long deptId) {
        return deptRepository.findById(deptId);
    }
}
