package com.shoppingbe.service.clock;

import com.shoppingbe.entity.MachineType;

import java.util.List;

public interface IMachineTypeService {
    List<MachineType> findAll();

    MachineType findById(Long id);
}
