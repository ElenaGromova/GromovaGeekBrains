package market;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private List<Product> cartProduts;
    private ProductRepositoryInterface productRepositoryInterface;

    @PostConstruct
    public void init(){
        cartProduts = new ArrayList<>(Arrays.asList());
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(cartProduts);
    }

    public void addToCart(int id){
        cartProduts.add(productRepositoryInterface.findProductById(id));
    }

    public void deleteFromToCart(int id){
        cartProduts.remove(productRepositoryInterface.findProductById(id));
    }
}
