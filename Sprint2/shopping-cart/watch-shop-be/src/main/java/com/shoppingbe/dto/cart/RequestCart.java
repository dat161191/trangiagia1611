package com.shoppingbe.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCart {
    private Long idCart;
    private Integer quanlityUpdate;
    private Long idAccount;

}
