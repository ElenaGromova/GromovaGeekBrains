package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.Optional;

@DataJpaTest// используется только слой репозиториев
@ActiveProfiles("test")//используются настройки ни приложения а из application-test/yaml
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void RepositoryTest() {
		Product p = new Product();
		p.setTitle("Tea");
		p.setPrice(100);
		entityManager.persist(p);
		entityManager.flush();

		List<Product> productList = productRepository.findAll();

		Assertions.assertAll(
				() -> {
					Assertions.assertNotNull(productRepository.findById(1L), "Product does not exist");
				},
				() -> {
					Assertions.assertNotSame(productRepository.findById(1L), productRepository.findById(2L), "Products match");
				},
				() -> {
					Assertions.assertEquals(4, productList.size(), "Another size");
				},
				() -> {
					Assertions.assertEquals("Bread", productList.get(0).getTitle(), "Product has another title");
				},
				() -> {
					productRepository.saveAndFlush(p);
					Assertions.assertSame(p, productList.get(3), "Product doesn't save");
				},
				() -> {
					productRepository.delete(p);
					Assertions.assertEquals(Optional.empty(), productRepository.findById(p.getId()), "Product doesn't delete");
				}
		);

	}

}