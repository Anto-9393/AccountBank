package com.example.contoCorrente.repository;

import com.example.contoCorrente.entity.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person,Long> {

}
