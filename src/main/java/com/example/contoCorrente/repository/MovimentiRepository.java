package com.example.contoCorrente.repository;

import com.example.contoCorrente.entity.Movimenti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovimentiRepository extends CrudRepository<Movimenti,Long> {
    @Query(value = "SELECT * FROM movimenti WHERE movimenti.id_conto=? ORDER BY ID DESC LIMIT 1",nativeQuery = true)
    Movimenti findImporto(long id);

    @Query(value = "SELECT * FROM movimenti WHERE movimenti.id_conto=? ORDER BY ID DESC LIMIT 5",nativeQuery = true)
    List findLastFiveOperations(long id);


}
