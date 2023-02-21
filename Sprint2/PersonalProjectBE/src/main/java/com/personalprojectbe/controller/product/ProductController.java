package com.personalprojectbe.controller.product;

import com.personalprojectbe.dto.product.ProductGetListDto;
import com.personalprojectbe.entity.Producer;
import com.personalprojectbe.service.IProducerService;
import com.personalprojectbe.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/pulic/product")
public class ProductController {
    @Autowired
    private IProducerService producerService;
    @Autowired
    private IProductService productService;

    @GetMapping("producer")
    public ResponseEntity<List<Producer>> getListProducer() {
        List<Producer> producerList = producerService.findAll();
        if (producerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(producerList, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<ProductGetListDto>> getList(@PageableDefault(size = 8) Pageable pageable) {
        Page<ProductGetListDto> productGetListDtos = productService.getListProduct(pageable);
        if (productGetListDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productGetListDtos, HttpStatus.OK);


    }
}
