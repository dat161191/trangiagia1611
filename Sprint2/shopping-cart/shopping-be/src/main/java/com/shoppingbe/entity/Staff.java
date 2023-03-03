package com.shoppingbe.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@DynamicUpdate
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
    @Column(columnDefinition = "bit default false")
    private boolean gender;
    @OneToOne
    private Account account;
}
