package com.conectbemodal.service;

import com.conectbemodal.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(Integer id);

    void save(Product customerType);

    void remove(Integer id);
}
