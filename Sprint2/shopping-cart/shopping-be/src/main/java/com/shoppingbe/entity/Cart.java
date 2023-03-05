package com.shoppingbe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@DynamicUpdate
@SQLDelete(sql = "UPDATE cart SET flag = true WHERE id=?")
@Where(clause = "flag=false")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantityPurchased;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    private boolean flag = false;
    private boolean status = false;
    @ManyToOne
    private Clock clock;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private Set<Orders> orders;

    public Cart(Long id, Integer quantityPurchased, Date createDate, Date modifyDate, Clock clock, Customer customer) {
        this.id = id;
        this.quantityPurchased = quantityPurchased;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.clock = clock;
        this.customer = customer;
    }
}