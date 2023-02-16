package com.personalprojectbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String email;
    private String phone;
    private String addressCustomer;
    @Column(columnDefinition = "date")
    private String birthday;
    @OneToOne
    private Account account;
    @ManyToOne
    @JoinColumn(name = "order_product_id",referencedColumnName = "id")
    private OrderProduct orderProduct;
}
