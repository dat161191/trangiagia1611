package com.conectbemodal.service;


import com.conectbemodal.model.Consignment;
import com.conectbemodal.repository.IConsignmentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsignmentService implements IConsignmentService {
    private final IConsignmentRepository consignmentRepository;

    public ConsignmentService(IConsignmentRepository consignmentRepository) {
        this.consignmentRepository = consignmentRepository;
    }

    @Override
    public Page<Consignment> getList(String productName, String importDate, Pageable pageable) {
        return consignmentRepository.getList(productName,importDate,pageable);
    }

    @Override
    public void save(Consignment consignment) {
        consignmentRepository.save(consignment);
    }

    @Override
    public void remove(Integer id) {
        consignmentRepository.deleteById(id);
    }

    @Override
    public Consignment findById(Integer id) {
        return consignmentRepository.findById(id).get();
    }
}
