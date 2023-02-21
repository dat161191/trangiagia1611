package com.personalprojectbe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameStaff;
    private String address;
    private String email;
    @Column(columnDefinition = "date")
    private String birthday;
    private String phone;
    private Integer salary;
    private String idCard;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    @Column(columnDefinition = "bit")
    private boolean gender;
    @OneToOne
    private Account account;

}
