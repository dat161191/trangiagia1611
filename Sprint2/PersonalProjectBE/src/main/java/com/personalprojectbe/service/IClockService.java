package com.personalprojectbe.service;

import com.personalprojectbe.dto.clock.ClockHomeDto;
import com.personalprojectbe.entity.Clock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClockService {
    Page<ClockHomeDto> getListClock(Pageable pageable,String search);

    List<ClockHomeDto> getListByTrademarkId(@Param("tradeMarkId") Long tradeMarkId);

    Clock findById(Long id);
}
