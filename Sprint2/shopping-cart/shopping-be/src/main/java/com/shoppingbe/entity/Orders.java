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
    @Column(columnDefinition = "date")
    private String bookingDate;
    private boolean paymentStatus;
    private String deliveryAddress;
    private String phone;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",columnDefinition = "DATE")
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date",columnDefinition = "DATE")
    private Date modifyDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Cart cart;
    @OneToMany(mappedBy = "orders")
    @JsonBackReference
    private Set<OrderDetail> orderDetails;

    public Orders(Long id, Long codeOrders, String bookingDate, boolean paymentStatus, String deliveryAddress, String phone, Date createDate, Date modifyDate, Customer customer) {
        this.id = id;
        this.codeOrders = codeOrders;
        this.bookingDate = bookingDate;
        this.paymentStatus = paymentStatus;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.customer = customer;
    }
}
