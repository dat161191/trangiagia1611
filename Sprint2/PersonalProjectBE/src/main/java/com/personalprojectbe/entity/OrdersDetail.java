package com.personalprojectbe.entity;

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
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trademark_id", referencedColumnName = "id")
    private Trademark trademark;
    @ManyToOne
    @JoinColumn(name = "order_product_id", referencedColumnName = "id")
    private OrderProduct orderProduct;
    private Integer quantityPurchased;
}
