package com.personalprojectbe.service;

import com.personalprojectbe.dto.product.ImgDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImgService {
    List<ImgDto> findByIdProduct(@Param("productId") Long productId);
}
