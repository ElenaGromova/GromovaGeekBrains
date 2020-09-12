package market;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository implements ProductRepositoryInterface{
    private List<Product> products;

    @PostConstruct
    public void init(){
       products = new ArrayList<>(Arrays.asList(
             new Product(1, "Bread", 30),
             new Product(2, "Milk", 40),
             new Product(3, "Eggs", 50),
             new Product(4, "Coffee", 60),
             new Product(5, "Tea", 70)
       ));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public void deleteProduct(Product product){
        if (products.contains(product)){
            products.remove(products.indexOf(product));
        }
        else {
            System.out.println("Продукт " + product.getTitle() + " отсутствует в корзине");
        }
    }

        public void deleteProductByTitle(String title) {
        for (Product pr : products) {
            if (pr.getTitle().equals(title)) {
                products.remove(products.indexOf(pr));
            }
//                else {
//                    System.out.println("Продукт " + title + " отсутствует в корзине");
//                }
        }
        throw new RuntimeException("Продукт " + title + " отсутствует в корзине");
    }

    public void addProductByTitle(Product product) {
        for (Product pr : products) {
            if (pr.equals(product)) {
                products.add(product);
            }
//                else {
//                    System.out.println("Продукт " + title + " отсутствует в корзине");
//                }
        }
        throw new RuntimeException("Продукт " + product.getTitle() + " отсутствует в корзине");
    }

        public Product findProductById(int id){
        for (Product pr : products){
            if (pr.getId() == id) {
                return pr;
            }
        }
        throw new RuntimeException("Продукт отсутствует в корзине");
        }
}
