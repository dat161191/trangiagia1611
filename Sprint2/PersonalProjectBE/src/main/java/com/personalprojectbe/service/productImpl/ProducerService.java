package com.personalprojectbe.service.productImpl;

import com.personalprojectbe.entity.Producer;
import com.personalprojectbe.repository.product.IProducerRepository;
import com.personalprojectbe.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProducerService implements IProducerService {
    @Autowired
    private IProducerRepository producerRepository;
    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }
}
