package market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private ProductRepositoryInterface productRepositoryInterface;

    @Autowired
    public void setProductRepository(ProductRepositoryInterface productRepositoryInterface){
        this.productRepositoryInterface = productRepositoryInterface;
    }

//    public ProductService(ProductRepositoryInterface productRepositoryInterface) {
//        this.productRepositoryInterface = productRepositoryInterface;
//    }

    public List<Product> findAll(){
        return productRepositoryInterface.findAll();
    }

    public void addProductByTitle(Product product) {
        productRepositoryInterface.addProductByTitle(product);
    }

    public void deleteProduct(Product product){
        productRepositoryInterface.deleteProduct(product);
    }

    public void deleteProductByTitle(String title){
        productRepositoryInterface.deleteProductByTitle(title);
    }

    public Product findProductById(int id){
        return productRepositoryInterface.findProductById(id);
    }


}
