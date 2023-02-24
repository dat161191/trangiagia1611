package com.personalprojectbe.repository.product;

import com.personalprojectbe.entity.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrademarkRepository extends JpaRepository<Trademark, Long> {
}
