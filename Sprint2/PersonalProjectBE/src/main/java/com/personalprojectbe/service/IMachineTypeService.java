package com.personalprojectbe.service;

import com.personalprojectbe.entity.MachineType;

import java.util.List;

public interface IMachineTypeService {
    List<MachineType> findAll();

    MachineType findById(Long id);
}
