package market;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        System.out.println(productService.findAll());
        CartService cartService = context.getBean("cartService", CartService.class);
        cartService.createCart();
        System.out.println("В корзине " + cartService.findAll());
        context.close();
    }

}
