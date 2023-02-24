package com.personalprojectbe.service.clockImpl;

import com.personalprojectbe.dto.clock.ClockHomeDto;
import com.personalprojectbe.entity.Clock;
import com.personalprojectbe.repository.product.IClockRepository;
import com.personalprojectbe.service.IClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockService implements IClockService {
    @Autowired
    private IClockRepository clockRepository;

    @Override
    public Page<ClockHomeDto> getListClock(Pageable pageable) {
        return clockRepository.getListClock(pageable);
    }

    @Override
    public List<ClockHomeDto> getListByTrademarkId(Long tradeMarkId) {
        return clockRepository.getListByTrademarkId(tradeMarkId);
    }

    @Override
    public Clock findById(Long id) {
        return clockRepository.findById(id).get();
    }
}
