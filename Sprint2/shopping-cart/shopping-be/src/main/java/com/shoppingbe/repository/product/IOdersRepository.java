package com.shoppingbe.repository.product;

import com.shoppingbe.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdersRepository extends JpaRepository<Orders,Long> {
}
