package com.example.contoCorrente.service;

import com.example.contoCorrente.entity.Conto;
import com.example.contoCorrente.entity.Movimenti;
import com.example.contoCorrente.entity.Person;
import com.example.contoCorrente.repository.ContoRepository;
import com.example.contoCorrente.repository.MovimentiRepository;
import com.example.contoCorrente.repository.PersonRepository;
import com.example.contoCorrente.utils.Type;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Sql(scripts="/schema.sql")
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @MockBean
    private ContoRepository contoRepository;
    @MockBean
    MovimentiRepository movimentiRepository;
    @MockBean
    PersonRepository personRepository;
    private Type tipo;

    /*
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
    */
    @Test
    void triggerMoney() throws Exception {
    }

    @Test
    void checkMoneyById() {
        long id = 1;
        Conto conto = new Conto(1, 1, 500);
        when(contoRepository.findById(id)).thenReturn(Optional.of(conto));
        assertNotNull(contoRepository.findById(id));
        assertEquals(500, accountService.checkMoneyById(id));


    }

    @Test
    void depositMoney() {
        Movimenti movimenti = new Movimenti(1, 1, Type.deposito, 500);
        movimentiRepository.save(movimenti);
        assertNotNull(movimenti.getTipo());
        assertNotNull(movimenti.getImporto());
        assertNotNull(movimenti.getId());
        assertEquals(500, movimenti.getImporto());
        assertEquals(1, movimenti.getId());
        assertEquals(Type.deposito, movimenti.getTipo());

    }

    @Test
    void lastFiveOperations() {
        List<Movimenti> movimentiList = new ArrayList<>();
        Movimenti movimenti = new Movimenti(1, 1, Type.deposito, 500);
        movimentiList.add((movimenti));
        when(movimentiRepository.findLastFiveOperations(1)).thenReturn(movimentiList);
        assertNotNull(movimenti);
        assertEquals(5, movimentiList.size());


    }
}