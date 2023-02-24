package com.personalprojectbe.service.clockImpl;

import com.personalprojectbe.entity.MachineType;
import com.personalprojectbe.repository.product.IMachineTypeRepository;
import com.personalprojectbe.service.IMachineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineTypeService implements IMachineTypeService {
    @Autowired
    private IMachineTypeRepository machineTypeRepository;

    @Override
    public List<MachineType> findAll() {
        return machineTypeRepository.findAll();
    }

    @Override
    public MachineType findById(Long id) {
        return machineTypeRepository.findById(id).get();
    }
}
