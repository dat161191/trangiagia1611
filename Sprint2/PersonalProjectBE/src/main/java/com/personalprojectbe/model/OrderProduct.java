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
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codeOrders;
    @Column(columnDefinition = "date")
    private String bookingDate;
    private boolean status;
    private boolean paymentStatus;
    private String deliveryAddress;
    private String phone;
}
