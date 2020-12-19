package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.PageDto;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.ProductFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(produces = "application/json") // /api/v1/products
    public PageDto getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                              @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        PageDto p = productService.findAllDtos(productFilter.getSpec(), page - 1, 5);
        return p;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
