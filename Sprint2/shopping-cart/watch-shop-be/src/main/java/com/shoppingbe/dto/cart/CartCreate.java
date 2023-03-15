package com.shoppingbe.dto.cart;

import com.shoppingbe.dto.clock.ClockDetailDto;
import com.shoppingbe.entity.Clock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartCreate {
    private Integer id;
    private Clock clock;
    private Long idAccount;
    private Integer quantityPurchased;
}
