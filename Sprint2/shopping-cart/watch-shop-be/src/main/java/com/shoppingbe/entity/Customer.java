package com.shoppingbe.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@DynamicUpdate
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String email;
    private String phone;
    private String addressCustomer;
    private String idCard;
    @Column(columnDefinition = "bit")
    private boolean gender;
    @Column(columnDefinition = "date")
    private String birthday;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private Set<Orders> orders;
}
