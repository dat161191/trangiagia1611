package com.shoppingbe.dto.cart;

import java.util.Date;

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

    /*New*/
    String getCreateDate();
    String getPayDate();

    boolean getStatus();
}
