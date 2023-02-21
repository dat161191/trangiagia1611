package com.personalprojectbe.repository.product;

import com.personalprojectbe.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducerRepository extends JpaRepository<Producer, Long> {
}
