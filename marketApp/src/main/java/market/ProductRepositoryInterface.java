package market;

import java.util.List;

public interface ProductRepositoryInterface {
    List<Product> findAll();
    void deleteProduct(Product product);
    void deleteProductByTitle(String title);
    Product findProductById(int id);
    void addProductByTitle(Product product);
}
