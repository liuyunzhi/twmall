package com.thoughtworks.thoughtworks_mall.repository;

import com.thoughtworks.thoughtworks_mall.entity.Product;

import java.util.List;

public interface ProductRespositoryCustom {

    List<Product> filter(Double minPrice,
                         Double maxPrice,
                         String brand,
                         String category,
                         Integer page,
                         Integer pageSize,
                         String order
    );
}
