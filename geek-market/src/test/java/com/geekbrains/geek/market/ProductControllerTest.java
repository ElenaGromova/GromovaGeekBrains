package com.geekbrains.geek.market;

import com.geekbrains.geek.market.dto.PageDto;
import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //подделка, чтобы не общаться с реальным сервером
@ActiveProfiles("h2")
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser(username = "Bob", roles = {"USER"})//с болванкой юзера для авторизации
    public void getAllProductsTest() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("music");

        Product p1 = new Product();
        p1.setId(1L);
        p1.setTitle("piano");
        p1.setPrice(100);
        p1.setCategory(category);

        Product p2 = new Product();
        p2.setId(2L);
        p2.setTitle("guitar");
        p2.setPrice(200);
        p2.setCategory(category);

        List<Product> productList = Arrays.asList(p1, p2);

        Specification<Product> spec = Specification.where(null);
        int page = 1;
        int size = 2;
        Page<Product> productPage = new PageImpl<>(productList, PageRequest.of(page, size), page);
        PageDto pageDto = new PageDto(productPage);
        given(productService.findAllDtos(spec, page, size)).willReturn(pageDto);
        System.out.println(pageDto);
        mvc.perform(get("/api/v1/products")
                    .param("page", "1")
                    .param("title", "")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productDtos[0].title", is(productPage.getContent().get(0).getTitle())));

    }

    @Test
    @WithMockUser(username = "Bob", roles = {"USER"})//с болванкой юзера для авторизации
    public void getProductByIdTest() throws Exception{
        Product product = new Product();
        product.setId(1L);
        product.setTitle("piano");
        product.setPrice(100);
        
        given(productService.findById(1L)).willReturn(Optional.of(product));

       mvc.perform(get("/api/v1/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("piano"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.title", is(product.getTitle())))
                .andExpect(jsonPath("$.price", is(product.getPrice())));
        
    }

    @Test
    @WithMockUser(username = "Bob", roles = {"USER"})//с болванкой юзера для авторизации
    public void createProductTest() throws Exception{
        Product product = new Product();
        product.setId(1L);
        product.setTitle("piano");
        product.setPrice(100);

        given(productService.saveOrUpdate(product)).willReturn(product);

        mvc.perform(post("/api/v1/products/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("piano"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.title", is(product.getTitle())))
                .andExpect(jsonPath("$.price", is(product.getPrice())));

    }


    @Test
    @WithMockUser(username = "Bob", roles = {"USER"})//с болванкой юзера для авторизации
    public void deleteProductsTest() throws Exception{

        mvc.perform(delete("/api/v1/products/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "Bob", roles = {"USER"})//с болванкой юзера для авторизации
    public void deleteProductByIdTest() throws Exception{

        mvc.perform(delete("/api/v1/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}

//productDtos: Array(5)
//        0: {id: 1, title: "Bread", price: 1, categoryTitle: "Food", $$hashKey: "object:29"}
//        1: {id: 2, title: "Samsung V100", price: 2, categoryTitle: "Smartphone", $$hashKey: "object:30"}
//        2: {id: 3, title: "Acer X1000", price: 3, categoryTitle: "Notebook", $$hashKey: "object:31"}
//        3: {id: 4, title: "Bread1", price: 1, categoryTitle: "Food", $$hashKey: "object:32"}
//        4: {id: 5, title: "Samsung1 V100", price: 2, categoryTitle: "Smartphone", $$hashKey: "object:33"}
//        length: 5