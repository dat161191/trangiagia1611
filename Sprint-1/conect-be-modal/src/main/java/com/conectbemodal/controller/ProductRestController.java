package com.conectbemodal.controller;

import com.conectbemodal.model.Product;
import com.conectbemodal.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("product")
public class ProductRestController {
    private final IProductService productService;

    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping("")
    public ResponseEntity<List<Product>> display() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
