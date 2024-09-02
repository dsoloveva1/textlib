package com.example.textlib;

import com.example.textlib.config.SecurityConfig;
import com.example.textlib.controllers.RecordsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql({"classpath:schema.sql"})
@Import(SecurityConfig.class)
public class LoginTest {
    @Autowired MockMvc mockMvc;

    @Test
    @WithMockUser
    @Sql({"classpath:data.sql"})
    void getAllRecordsAuthorized() throws Exception {
        mockMvc.perform(get("/records", 1L))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void getAllRecordsIfNotAuthorized() throws Exception {
        mockMvc.perform(get("/records", 1L))
                .andExpect(status().isUnauthorized());
    }
}
