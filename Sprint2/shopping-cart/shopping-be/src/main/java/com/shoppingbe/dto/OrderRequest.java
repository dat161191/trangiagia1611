package com.shoppingbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Long id;
    private String customerName;
    private String phone;
    private String email;
    private String addressCustomer;
    private Integer totalOrder;
    private String deliveryAddress;

}
