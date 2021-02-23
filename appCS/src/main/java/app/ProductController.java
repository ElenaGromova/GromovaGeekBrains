package app;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public String productList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        logger.info("Get All products String");
        return "products";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Product> allProducts(){
        List<Product> productList = productService.findAll();
        logger.info("Get All products List");
        return productList;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
        logger.info("Get product by id");
        return product;
    }

    @PostMapping("/add_product")
    @ResponseBody
    public String addProduct(@RequestParam(name = "product_title") String title,
                             @RequestParam(name = "product_price") int price) {

        Product product = new Product(title, price);
        productService.add(product);
        logger.info("Post product String");
        return "product added";
    }


    @GetMapping("/edit/{id}")
   // @ResponseBody
    public String showProduct(@PathVariable Long id, Model model){
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("product", p);
        logger.info("Get edit product Product");
//        return "edit product";
        return "product";
    }

    @PostMapping("/edit")
    //@ResponseBody
    public String saveProduct(@ModelAttribute Product product){
        productService.saveOrUpdate(product);
        logger.info("Post edit product Product");
       // System.out.println(request.getPathInfo());
        return "redirect:/products";
    }
}
