package com.shoppingbe.service.clock.clockImpl;

import com.shoppingbe.dto.clock.ClockHomeDto;
import com.shoppingbe.entity.Clock;
import com.shoppingbe.repository.product.IClockRepository;
import com.shoppingbe.service.clock.IClockService;
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
    public Page<ClockHomeDto> getListClock(Pageable pageable, String search) {
        return clockRepository.getListClock(pageable,search);
    }

    @Override
    public List<ClockHomeDto> getListByTrademarkId(Long tradeMarkId) {
        return clockRepository.getListByTrademarkId(tradeMarkId);
    }

    @Override
    public Clock findById(Long id) {
        return clockRepository.findById(id).get();
    }

    @Override
    public void save(Clock clock) {
        clockRepository.save(clock);
    }

    @Override
    public List<ClockHomeDto> getListCarousel() {
        return clockRepository.getListCarousel();
    }
}
