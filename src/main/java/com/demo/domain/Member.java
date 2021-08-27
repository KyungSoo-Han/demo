package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long member_id;
    private String name;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;

    private String email;
    private String gender;
    private String enter_dt;
    private String tel_no;
    private String hp_no;

    @Column(name = "activated")
    @Enumerated(EnumType.STRING)
    private MemberActivited activated;

    @Builder
    public Member(String name, String password, String email, String enter_dt,
                  String tel_no, String hp_no, String gender, Department department) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.enter_dt = enter_dt;
        this.tel_no = tel_no;
        this.hp_no = hp_no;
        this.gender = gender;
        this.activated = MemberActivited.OFF;
        this.department = department;

    }

}
