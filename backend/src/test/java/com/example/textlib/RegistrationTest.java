package com.example.textlib;

import com.example.textlib.config.SecurityConfig;
import com.example.textlib.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql({"classpath:schema.sql"})
@Import(SecurityConfig.class)
public class RegistrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void registerIfNewUser() throws Exception {
        User user = new User(1L, "", "", true, "ROLE_USER");

        this.mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                        .andExpect(status().isOk());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void registerIfUserAlreadyRegistered() throws Exception {
        User user = new User(1L, "dsoloveva@gmail.com", "123", true, "ROLE_USER");

        this.mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                        .andExpect(status().isConflict());
    }


}
