package com.thoughtworks.thoughtworks_mall.repository;

import com.thoughtworks.thoughtworks_mall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRespositoryCustom {

}
