package com.personalprojectbe.service;

import com.personalprojectbe.entity.Producer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProducerService {
    List<Producer> findAll();
}
