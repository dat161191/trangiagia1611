package com.personalprojectbe.service;

import com.personalprojectbe.dto.clock.ImgDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImgService {
    List<ImgDto> findByIdClock(@Param("clockId") Long clockId);
}
