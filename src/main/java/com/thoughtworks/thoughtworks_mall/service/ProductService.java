package com.thoughtworks.thoughtworks_mall.service;

import com.thoughtworks.thoughtworks_mall.entity.Product;
import com.thoughtworks.thoughtworks_mall.exception.ProductNotFoundException;
import com.thoughtworks.thoughtworks_mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void update(Long id, Product product) throws ProductNotFoundException {
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException());


            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setUnit(product.getUnit());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setBrand(product.getBrand());
            oldProduct.setImage(product.getImage());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setProductionDate(product.getProductionDate());
            oldProduct.setProductionPlace(product.getProductionPlace());
            productRepository.save(oldProduct);
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAll(Double minPrice, Double maxPrice, String brand, String category, Integer page, Integer pageSize, String order) {
        return productRepository.filter(minPrice, maxPrice, brand, category, page, pageSize, order);
    }
}
