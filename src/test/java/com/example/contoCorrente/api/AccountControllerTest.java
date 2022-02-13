package com.example.contoCorrente.api;

import com.example.contoCorrente.entity.Person;
import com.example.contoCorrente.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testPersonById() throws Exception{
        when(accountService.getPersonById(1)).thenReturn(Optional.of(new Person("ciao","ciao")));

       this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bank/").
                      contentType(MediaType.APPLICATION_JSON).
                content("{\"id\":\"1\"}"))
                    .andExpect(MockMvcResultMatchers.jsonPath(accountService.getPersonById(1).get().getName()).value("ciao") )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}