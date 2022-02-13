package com.example.contoCorrente.service;


import com.example.contoCorrente.entity.Conto;
import com.example.contoCorrente.entity.Movimenti;
import com.example.contoCorrente.entity.Person;
import com.example.contoCorrente.repository.PersonRepository;
import com.example.contoCorrente.repository.ContoRepository;
import com.example.contoCorrente.repository.MovimentiRepository;
import com.example.contoCorrente.utils.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ContoRepository contoRepository;
    @Autowired
    private MovimentiRepository movimentiRepository;

    public Optional<Person> getPersonById(long id) throws Exception {
        Optional<Person> person = personRepository.findById(id);
        if(person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return person;
    }

    public int triggerMoney(long id, Movimenti movimenti) throws Exception {
        Conto accountBank = contoRepository.findById(id).get();
        int currentBalance = accountBank.getSaldo();
        int deposit = movimenti.getImporto();
        Type type = movimenti.getTipo();
        int newDeposit=0;
        if(type.equals(Type.deposito)) {
            newDeposit = currentBalance + deposit;
        }else if(currentBalance >= deposit) {
            newDeposit = currentBalance - deposit;
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        accountBank.setSaldo((newDeposit));
        contoRepository.save(accountBank);
        return 0;
    }

    public int checkMoneyById(long id) {
        return contoRepository.findById(id).get().getSaldo();

    }

    public void depositMoney(long id, Movimenti movimenti) throws Exception {
        movimentiRepository.save(movimenti);
        triggerMoney(id, movimenti);

    }

    public List<Movimenti> lastFiveOperations(long id) {
        return movimentiRepository.findLastFiveOperations(id);
    }
}
