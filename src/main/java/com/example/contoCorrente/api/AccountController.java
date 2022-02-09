package com.example.contoCorrente.api;

import com.example.contoCorrente.entity.Movimenti;
import com.example.contoCorrente.entity.Person;
import com.example.contoCorrente.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("api/v1/bank")
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {this.accountService = accountService;
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable ("id") long id) throws Exception {
        return  accountService.getPersonById(id).orElse(null);
    }
    @GetMapping(path = "balance/{id}")
    public int checkMoneyById(@PathVariable ("id") long id){
        return accountService.checkMoneyById(id);
    }

    @GetMapping(path="operations/{id}")
    public List lastFiveOperations(@PathVariable ("id") long id) {return accountService.lastFiveOperations(id);}

    @PostMapping(path = "operations/{id}")
    public void depositMoney(@PathVariable ("id") long id, @Valid @NonNull @RequestBody Movimenti movimenti) throws Exception{
       accountService.depositMoney(id,movimenti);



    }



 }
