package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.dto.PageDto;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public PageDto findAllDtos(Specification<Product> spec, int page, int size) {
        Page<Product> productPage = productRepository.findAll(spec, PageRequest.of(page, size));
        return new PageDto(productPage);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }
}
