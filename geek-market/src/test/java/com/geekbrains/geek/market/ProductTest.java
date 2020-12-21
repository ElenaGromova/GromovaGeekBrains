package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class ProductTest {
    @Autowired
    private Product p;

    @Autowired
    private Category c;

    @Test
    public  void  productEntityTest(){
		c = new Category();
		c.setId(1L);
		p = new Product();
		p.setTitle("Samsung V100");
		p.setPrice(1);
		p.setCategory(c);
		
        //чтобы увидеть все ошибки сразу
        Assertions.assertAll(
                () -> {
                    Assertions.assertNotNull(p, "Product does not exist");
                },
				() -> {
                    Assertions.assertNotNull(c, "Category does not exist");
                },
				() -> {
                    Assertions.assertEquals(1, p.getPrice(), "Price is incorrect");
                },
                () -> {
                    Assertions.assertEquals(1, p.getCategory().getId(), "Category is incorrect");
                },
                () -> {
                    Assertions.assertEquals("Samsung V100", p.getTitle(), "Title is incorrect");
                }
        );



//        Assertions.assertDoesNotThrow( () -> {}  ); --- прокидыем эксепшн или любой, код не должен его выбрасывать
//        Assertions.assertFalse --- возвращается фолс
//        Assertions.assertIterableEquals --- коллекции одинаковые
//        Assertions.assertLinesMatch(); --- наборы строк совпадают
//        Assertions.assertNotEquals(); --- не равны
//        Assertions.assertNotNull(); --- ссылка не пустая
//        Assertions.assertNotSame --- две ссылки не ведут к одному объекту в хипе
//        Assertions.assertNull --- ссылка пустая
//        Assertions.assertSame(); --- две ссылки ведут к одному объекту
//        Assertions.fail(); --- намеренно валим тест
//        Assertions.assertThrows(ArithmeticException.class, () -> {int x = 10/0}); --- задаем эксепшн и что хотим сделать чтобы он вылетед

    }


}
