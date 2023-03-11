package com.shoppingbe.repository.order;

import com.shoppingbe.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomer_Id(Long id);
}
