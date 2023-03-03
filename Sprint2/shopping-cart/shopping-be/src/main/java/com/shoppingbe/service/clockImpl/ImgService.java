package com.shoppingbe.service.clockImpl;

import com.shoppingbe.dto.clock.ImgDto;
import com.shoppingbe.repository.product.IImgListRepository;
import com.shoppingbe.service.IImgService;
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
