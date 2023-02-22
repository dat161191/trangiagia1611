package com.personalprojectbe.service.clockImpl;

import com.personalprojectbe.entity.MachineType;
import com.personalprojectbe.repository.product.IHostTypeRepository;
import com.personalprojectbe.service.IHostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HostTypeService implements IHostTypeService {
    @Autowired
    private IHostTypeRepository hostTypeRepository;
    @Override
    public List<MachineType> findAll() {
        return hostTypeRepository.findAll();
    }
}
