package com.example.contoCorrente.repository;

import com.example.contoCorrente.entity.Conto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ContoRepository extends CrudRepository<Conto,Long> {




}
