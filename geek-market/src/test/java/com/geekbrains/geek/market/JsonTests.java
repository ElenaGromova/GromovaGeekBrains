package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> jackson;

    @Autowired
    private JacksonTester<User> jacksonUser;

    @Test
    public void jsonRoleSerializationTest() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        // {
        //   "id": 1,
        //   "name": "USER"
        // }
        assertThat(this.jackson.write(role))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.name").isEqualTo("USER");
    }

    @Test
    public void jsonRoleDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
        Role realRole = new Role();
        realRole.setId(2L);
        realRole.setName("ADMIN");

        assertThat(this.jackson.parse(content)).isEqualTo(realRole);
        assertThat(this.jackson.parseObject(content).getName()).isEqualTo("ADMIN");
    }

    @Test
    public void jsonUserSerializationTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("Bob");
        user.setPassword("100");

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ADMIN");

        Role role2= new Role();
        role2.setId(2L);
        role2.setName("USER");

        List<Role> roles = Arrays.asList(role1, role2);
        user.setRoles(roles);
   
        assertThat(this.jacksonUser.write(user))
                .hasJsonPathNumberValue("$.id")
                .hasJsonPathStringValue("$.username")
                .hasJsonPathArrayValue("$.roles");

        assertThat(this.jacksonUser.write(user)).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(this.jacksonUser.write(user)).extractingJsonPathStringValue("$.username").isEqualTo("Bob");
        assertThat(this.jacksonUser.write(user)).extractingJsonPathStringValue("$.roles[0]").isEqualTo("ADMIN");
        assertThat(this.jacksonUser.write(user)).extractingJsonPathStringValue("$.roles[1]").isEqualTo("USER");
    }

    @Test
    public void jsonUserDeserializationTest() throws Exception {
        String content = "{\"id\": 1, \"username\":\"Bob\", \"password\":\"100\", \n" +
                " \"roles\": [\n {\n \"id\": 1,\n \"name\": \"ADMIN\"\n },\n {\n \"id\": 2,\n \"name\": \"USER\"\n}\n]\n}";
        System.out.println(content);
        User user = new User();
        user.setId(1L);
        user.setUsername("Bob");
        user.setPassword("100");

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ADMIN");

        Role role2= new Role();
        role2.setId(2L);
        role2.setName("USER");

        List<Role> roles = Arrays.asList(role1, role2);
        user.setRoles(roles);

        assertThat(this.jacksonUser.parse(content)).isEqualTo(user);
        assertThat(this.jacksonUser.parseObject(content).getUsername()).isEqualTo("Bob");
        assertThat(this.jacksonUser.parseObject(content).getId()).isEqualTo(1L);
        assertThat(this.jacksonUser.parseObject(content).getPassword()).isEqualTo("100");
        assertThat(this.jacksonUser.parseObject(content).getRoles()).containsAll(roles);
    }
}