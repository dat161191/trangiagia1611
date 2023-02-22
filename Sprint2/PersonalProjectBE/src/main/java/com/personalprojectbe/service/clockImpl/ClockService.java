package com.personalprojectbe.service.productImpl;

import com.personalprojectbe.dto.product.ProductGetListDto;
import com.personalprojectbe.repository.product.IProductRepository;
import com.personalprojectbe.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Page<ProductGetListDto> getListProduct(Pageable pageable) {
        return productRepository.getListProduct(pageable);
    }
}
