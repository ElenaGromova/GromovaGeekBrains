package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
// проверка работатоспособности сервера с тестовой бд
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullRestTest() {
        List<User> userList = restTemplate.getForObject("/api/v1/users", List.class);
        assertThat(userList).isNotNull();
        assertThat(userList).isNotEmpty();
    }
}