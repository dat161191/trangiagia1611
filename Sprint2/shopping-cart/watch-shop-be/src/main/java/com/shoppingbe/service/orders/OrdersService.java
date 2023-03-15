package com.shoppingbe.service.orders;

import com.shoppingbe.entity.Orders;
import com.shoppingbe.repository.order.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrdersRepository ordersRepository;

    @Override
    public List<Orders> historyOrder(Long id) {
        return ordersRepository.findByCustomer_Id(id);
    }

    @Override
    public void save(Orders orders) {
        ordersRepository.save(orders);
    }
}
