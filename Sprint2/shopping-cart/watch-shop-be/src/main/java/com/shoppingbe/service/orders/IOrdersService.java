package com.shoppingbe.service.orders;

import com.shoppingbe.entity.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> historyOrder(Long id);
    void save(Orders orders);

}
