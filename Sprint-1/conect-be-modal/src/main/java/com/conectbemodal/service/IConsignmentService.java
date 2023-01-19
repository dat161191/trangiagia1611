package com.conectbemodal.service;


import com.conectbemodal.model.Consignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConsignmentService {
    Page<Consignment> getList(String productName, String importDate, Pageable pageable);

    void save(Consignment consignment);
    void remove(Integer id);

    Consignment findById(Integer id);
}
