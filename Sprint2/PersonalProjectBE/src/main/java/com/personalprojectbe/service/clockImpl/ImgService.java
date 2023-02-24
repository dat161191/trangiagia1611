package com.personalprojectbe.service.clockImpl;

import com.personalprojectbe.dto.clock.ImgDto;
import com.personalprojectbe.repository.product.IImgListRepository;
import com.personalprojectbe.service.IImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService implements IImgService {
    @Autowired
    private IImgListRepository iImgListRepository;

    @Override
    public List<ImgDto> findByIdClock(Long productId) {
        return iImgListRepository.findByIdClock(productId);
    }
}
