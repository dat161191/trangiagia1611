package com.shoppingbe.service.clock.clockImpl;


import com.shoppingbe.entity.Trademark;
import com.shoppingbe.repository.product.ITrademarkRepository;
import com.shoppingbe.service.clock.ITrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrademarkService implements ITrademarkService {
    @Autowired
    private ITrademarkRepository trademarkRepository;

    @Override
    public List<Trademark> findAll() {
        return trademarkRepository.findAll();
    }

    @Override
    public Trademark findById(Long id) {
        return trademarkRepository.findById(id).get();
    }
}
