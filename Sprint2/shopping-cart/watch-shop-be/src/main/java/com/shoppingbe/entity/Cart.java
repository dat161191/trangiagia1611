package com.shoppingbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "create_date", columnDefinition = "DATETIME")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date", columnDefinition = "DATETIME")
    private Date modifyDate;
    @JoinColumn(columnDefinition = "DATETIME")
    private Date payDate;
    private boolean flag = false;
    private boolean status = false;
    @ManyToOne
    private Clock clock;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    @JsonBackReference
    private Set<Orders> orders;

    public Cart(Long id, Integer quantityPurchased, Date createDate, Date modifyDate, Clock clock, Customer customer) {
        this.id = id;
        this.quantityPurchased = quantityPurchased;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.clock = clock;
        this.customer = customer;
    }

    public Cart(Long id, Integer quantityPurchased, Date createDate, Date modifyDate, Date payDate, Clock clock, Customer customer) {
        this.id = id;
        this.quantityPurchased = quantityPurchased;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.payDate = payDate;
        this.clock = clock;
        this.customer = customer;
    }
}
