package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("h2")
public class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    @Test
    public void cartEntityTest(){
		Product product = new Product();
        product.setId(1L);
        product.setTitle("piano");
        product.setPrice(100);

        Mockito.doReturn(Optional.of(product))
                .when(productService)
                .findById(1L);
        cart.addOrIncrement(product.getId());

        Assertions.assertAll(
                () -> {
                    Assertions.assertNotNull(cart.getItems().get(0).getProduct());
                },
                () -> {
                    Assertions.assertEquals(1, cart.getItems().size());
                },
				() -> {
                    Assertions.assertEquals(product.getTitle(), cart.getItems().get(0).getProduct().getTitle());
                },
				() -> {
                    cart.addOrIncrement(product.getId());
                    Assertions.assertEquals(1, cart.getItems().size());
                },
                () -> {
                    Assertions.assertEquals(2, cart.getItems().get(0).getQuantity());
                },
                () -> {
                    cart.recalculate();
                    Assertions.assertEquals(200, cart.getPrice());
                },
                () -> {
                    cart.decrementOrRemove(product.getId());
                    Assertions.assertEquals(1, cart.getItems().size());
                },
                () -> {
                    cart.clear();
                    Assertions.assertEquals(0, cart.getItems().size());
                }
        );
    }


}
