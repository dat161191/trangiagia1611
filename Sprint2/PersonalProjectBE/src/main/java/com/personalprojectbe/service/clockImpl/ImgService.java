package com.personalprojectbe.service.productImpl;

import com.personalprojectbe.dto.product.ImgDto;
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
    public List<ImgDto> findByIdProduct(Long productId) {
        return iImgListRepository.findByIdProduct(productId);
    }
}
