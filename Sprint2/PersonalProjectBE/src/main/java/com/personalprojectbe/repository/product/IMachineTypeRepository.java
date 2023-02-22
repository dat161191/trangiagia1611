package com.personalprojectbe.repository.product;

import com.personalprojectbe.entity.MachineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHostTypeRepository extends JpaRepository<MachineType,Long> {
}
