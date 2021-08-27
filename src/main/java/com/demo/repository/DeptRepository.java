package com.demo.repository;

import com.demo.domain.Department;
import com.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class DeptRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Department dept) {
        em.persist(dept);
    }

    public Department findById(Long dept_id) {
        return em.find(Department.class, dept_id);
    }
}
