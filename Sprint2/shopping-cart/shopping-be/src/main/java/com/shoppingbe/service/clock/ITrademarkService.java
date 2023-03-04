package com.shoppingbe.service.clock;

import com.shoppingbe.entity.Trademark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITrademarkService {
    List<Trademark> findAll();

    Trademark findById(Long id);

}
