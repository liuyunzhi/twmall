package com.thoughtworks.thoughtworks_mall.service;

import com.thoughtworks.thoughtworks_mall.entity.Product;
import com.thoughtworks.thoughtworks_mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void add(Product product) {
        productRepository.save(product);
    }
}
