package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // ManyToOne 조인일때 적은
public class Department {

    @Id
    @GeneratedValue
    private Long dept_id;
    private String dept_nm;
    private String remark;
    /*
    @JsonIgnore  // 양방향으로 조인되는경우 한쪽을 @JsonIgnore 해줘야 한다.  안하면 무한루프...
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Member> member = new ArrayList<>();

    */


    @Builder
    public Department(String dept_nm, String remark) {
        this.dept_nm = dept_nm;
        this.remark = remark;
    }
}
