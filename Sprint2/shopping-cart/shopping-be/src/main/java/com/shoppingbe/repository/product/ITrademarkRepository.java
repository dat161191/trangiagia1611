package com.shoppingbe.repository.product;

import com.shoppingbe.entity.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrademarkRepository extends JpaRepository<Trademark, Long> {
}
