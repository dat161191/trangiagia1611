package com.shoppingbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@DynamicUpdate
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codeOrders;

    //    private boolean paymentStatus;
    private String deliveryAddress;
    private String phone;
    private Integer totalOrder;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "DATETIME")
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date", columnDefinition = "DATETIME")
    private Date modifyDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Cart cart;
    @OneToMany(mappedBy = "orders")
    @JsonBackReference
    private Set<OrderDetail> orderDetails;

    public Orders(Long id, Long codeOrders, String deliveryAddress, String phone, Integer totalOrder) {
        this.id = id;
        this.codeOrders = codeOrders;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.totalOrder = totalOrder;
    }
}
