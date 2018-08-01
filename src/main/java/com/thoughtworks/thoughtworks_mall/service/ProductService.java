package com.thoughtworks.thoughtworks_mall.service;

import com.thoughtworks.thoughtworks_mall.entity.Product;
import com.thoughtworks.thoughtworks_mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void add(Product product) {
        productRepository.save(product);
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public void update(Long id, Product product) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product oldProduct = optional.get();
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setBrand(product.getBrand());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setProductionDate(product.getProductionDate());
            oldProduct.setProductionPlace(product.getProductionPlace());
            productRepository.save(oldProduct);
        }
    }
}
