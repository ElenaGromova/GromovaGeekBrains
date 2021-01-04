Decorator - есть просто ПК, а есть ПК с установленной ОС

public interface Product{
    public int getPrice();
    public String getTitle();
}

public class SimpleProduct implements Product{
    private int price = 50;
    private String title = "notebook";

  @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getTitle() {
        return this.title;
    }
}

public class BuildProduct implements Product{
    private SimpleProduct simpleProduct;

   public BuildProduct(SimpleProduct simpleProduct){
   this.simpleProduct = simpleProduct;
}

 @Override
    public int getPrice() {
        return this.simpleProduct.getPrice() + 100;
    }

    @Override
    public String getTitle() {
        return this.simpleProduct.getTitle() + " with installed Windows";
    }
}

public class App {
    public static void main(String[] args) {

    Product simpleProduct = new SimpleProduct();
    System.out.println("Price of simple product - " + simpleProduct.getPrice());
    System.out.println("Title of simple product - " + simpleProduct.getTitle());


    Product buildProduct = new BuildProduct(simpleProduct);
    System.out.println("Price of build product - " + buildProduct.getPrice());
    System.out.println("Title of build product - " + buildProduct.getTitle());
}

======
Facade - есть продукты и категории, мы хотим узнать, какие продукты принадлежат категории

public class ProductService{
    public List<Product> getProductsByCategoryId(Integer categoryId);
}

public class CategoryService{
    public Category getCategoryById(Integer id);
    public Integer getId();
    public String getTitle();
}

public class ProductsOfCategoryFacade{
    private ProductService productService;
    private CategoryService categoryService;

    public ProductsOfCategoryFacade(ProductService productService, CategoryService categoryService){
     this.productService = productService;
     this.categoryService = categoryService;
   }

    public List<Product> getCategoryProducts(Integer categoryId){
     Category category = this.categoryService.getCategoryById(categoryId);
     List<Product> productList = this.productService.getProductsByCategoryId(category.getId());
     return productList;
    }

    public String getCategoryTitle(Integer categoryId){
    Category category = this.categoryService.getCategoryById(categoryId);
    return category.getTitle();
    }
}

public class App {
    public static void main(String[] args) {
    public ProductService productService;
    public CategoryService categoryService;

    ProductsOfCategoryFacade productsOfCategoryFacade = new ProductsOfCategoryFacade(productService, categoryService);
    System.out.println("Products of category - " + productsOfCategoryFacade.getCategoryTitle(1) + ": " 
                                                 + productsOfCategoryFacade.getCategoryProducts(1));
   
}


