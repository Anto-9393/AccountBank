package com.example.contoCorrente.api;

import com.example.contoCorrente.entity.Conto;
import com.example.contoCorrente.entity.Movimenti;
import com.example.contoCorrente.entity.Person;
import com.example.contoCorrente.repository.ContoRepository;
import com.example.contoCorrente.repository.MovimentiRepository;
import com.example.contoCorrente.service.AccountService;
import com.example.contoCorrente.utils.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    @MockBean
    private MovimentiRepository movimentiRepository;
    @MockBean
    private ContoRepository contoRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPersonById() throws Exception {
        Person person = new Person("ciao", "ciao", 1);
        when(accountService.getPersonById(1)).thenReturn(Optional.of(person));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bank/{id}", 1).
                        accept(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ciao"));
    }

    @Test
    public void testMoneyById() throws Exception {
        long id = 1;
        Conto conto = new Conto(1, 1, 500);
        when(accountService.checkMoneyById(id)).thenReturn(conto.getSaldo());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bank/balance/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(500));
    }

    @Test
    public void testDepositMoney() throws Exception {
        long id = 1;
        Movimenti movimenti = new Movimenti(1, 1, Type.deposito, 500);
        when(movimentiRepository.save(movimenti)).thenReturn(movimenti);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bank/operations/{id}", 1)
                        .content(asJsonString(movimenti))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testFiveOperatins() throws Exception {
        List <Movimenti> operations = new ArrayList<>();
        operations.add(new Movimenti(1,1,Type.deposito,500));
        operations.add(new Movimenti(1,1,Type.deposito,500));
        operations.add(new Movimenti(1,1,Type.deposito,500));
        operations.add(new Movimenti(4,1,Type.deposito,500));
        operations.add(new Movimenti(5,1,Type.deposito,500));
        when(accountService.lastFiveOperations(1)).thenReturn(operations);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bank/operations/{id}",1))
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(5)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}