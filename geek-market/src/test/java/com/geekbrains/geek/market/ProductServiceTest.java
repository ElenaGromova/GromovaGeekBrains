package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.ProductRepository;
import com.geekbrains.geek.market.repositories.specifications.ProductSpecifications;
import com.geekbrains.geek.market.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import java.util.*;

@SpringBootTest
@ActiveProfiles("h2")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean //пустой объект без логики
    private ProductRepository productRepository;

    @Test
    public void findByIdProductTest() {
        Product p = new Product();
        p.setTitle("Tea");
        p.setPrice(100);

        //когда мы запросим у болванки репозитория метод с искусственным параметром, то он нам вернет искуственный продукт
        Mockito.doReturn(Optional.of(p))
                .when(productRepository)
                .findById(5L);

        Product product = productService.findById(5L).get();
        Assertions.assertNotNull(product, "Product does not exist");
        Assertions.assertAll(
                () -> {
                    Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(5L));
                },
                () -> {
                    Assertions.assertEquals(100, product.getPrice(), "Price is incorrect");
                },
                () -> {
                    Assertions.assertEquals("Tea", product.getTitle(), "Title is incorrect");
                }

        );
    }

    @Test
    public void deleteByIdProductTest() {
        productService.deleteById(5L);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(ArgumentMatchers.eq(5L));
    }


    @Test
    public void findAllProductTest() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("music");

        Product p1 = new Product();
        p1.setTitle("piano");
        p1.setPrice(100);
        p1.setCategory(category);

        Product p2 = new Product();
        p2.setTitle("guitar");
        p2.setPrice(200);
        p2.setCategory(category);

        List<Product> productList = Arrays.asList(p1, p2);
        Specification<Product> spec = Specification.where(null);
        spec = spec.and(ProductSpecifications.categoryIdIs(1L));
        int page = 1;
        int size = 2;
        Page<Product> productPage = new PageImpl<>(productList, PageRequest.of(page, size), page);

        Mockito.doReturn(productPage)
                .when(productRepository)
                .findAll(spec, PageRequest.of(page, size));

        Page<Product> serviceProductList = productService.findAll(spec, page, size);

        Assertions.assertAll(
                () -> {
                    Assertions.assertIterableEquals(productList, serviceProductList.getContent(), "lists doesn't same");
                },
                () -> {
                    Assertions.assertSame(p1, serviceProductList.getContent().get(0), "products doesn't same");
                },
                () -> {
                    Assertions.assertEquals(size, serviceProductList.getTotalPages(), "list size incorrect");
                },
                () -> {
                    Assertions.assertEquals(page, serviceProductList.getNumber(), "page number incorrect");
                },
                () -> {
                    Assertions.assertNotNull(serviceProductList.getContent(), "list is empty");
                }
        );
    }

    @Test
    public void saveProductTest() {
        Product p = new Product();
        p.setTitle("piano");
        p.setPrice(100);

        Mockito.doReturn(p)
                .when(productRepository)
                .save(p);

        Product product = productService.saveOrUpdate(p);
        Assertions.assertAll(

                () -> {
                    Assertions.assertSame(p, product, "products doesn't same");
                },
                () -> {
                    Assertions.assertEquals("piano", product.getTitle(), "title incorrect");
                },
                () -> {
                    Assertions.assertEquals(100, product.getPrice(), "price incorrect");
                },
                () -> {
                    Assertions.assertNotNull(product, "product doesn't save");
                }

        );
    }


}



