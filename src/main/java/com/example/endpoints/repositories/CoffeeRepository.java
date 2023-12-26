package com.example.endpoints.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.endpoints.entities.Coffee;

public interface CoffeeRepository extends CrudRepository<Coffee, String>{

}
