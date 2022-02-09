package com.example.contoCorrente.service;

import com.example.contoCorrente.entity.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void getPersonById() throws Exception {
        long id = 5;
        AccountService service = new AccountService();
        Optional<Person> personById = service.getPersonById(id);
        assertNotNull(personById);
        assertNotNull(personById.get());
        //TODO H2, jUnit, Integration test MockMVC, mock query con H2 file.sql in resources packeage
        //TODO MOCKITO when(repo.find()).thenReturn(new Person("pippo", 12))
    }

    @Test
    void triggerMoney() { 
    }

    @Test
    void checkMoneyById() {
    }

    @Test
    void depositMoney() {
    }

    @Test
    void lastFiveOperations() {
    }
}