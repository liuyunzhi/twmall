package com.thoughtworks.thoughtworks_mall.controller;

import com.thoughtworks.thoughtworks_mall.entity.Product;
import com.thoughtworks.thoughtworks_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity add(@RequestBody Product product) {
        productService.add(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Product product) {
        productService.update(id, product);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        Product product = productService.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                 @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                 @RequestParam(value = "brand", required = false) String brand,
                                 @RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(value = "order", required = false, defaultValue = "ASC") String order) {
        return ResponseEntity.ok(productService.getAll(minPrice, maxPrice, brand, category, page, pageSize, order));
    }
}
