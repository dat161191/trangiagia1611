package com.shoppingbe.dto.cart;

/**
 * 04/03/2023
 */
public interface CartListByIdAccount {
    Long getCartId();

    Long getClockId();

    Long getCustomerId();

    Double getClockPrince();

    Integer getQuanlityCart();

    Integer getQuanlityClock();

    String getUrl();

    String getClockName();
}
