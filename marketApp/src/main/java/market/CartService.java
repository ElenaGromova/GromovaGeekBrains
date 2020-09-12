package market;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CartService {
    private Cart cart;

    public void createCart(){
        cart.addToCart(1);
        cart.addToCart(2);
        cart.addToCart(3);
        cart.addToCart(4);
        cart.addToCart(5);
        cart.deleteFromToCart(5);
        cart.findAll();
    }

    public List<Product> findAll(){
        return cart.findAll();
    }

}
