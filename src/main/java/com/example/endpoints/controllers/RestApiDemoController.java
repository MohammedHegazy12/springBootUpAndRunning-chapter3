package com.example.endpoints.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.endpoints.entities.Coffee;
import com.example.endpoints.repositories.CoffeeRepository;

@RestController
@RequestMapping("/coffees")
public class RestApiDemoController {
	private final CoffeeRepository coffeeRepository;

	@Autowired
	public RestApiDemoController(CoffeeRepository coffeeRepository) {
		this.coffeeRepository = coffeeRepository;
		coffeeRepository.saveAll(
				List.of(new Coffee("cafe Cereze"), 
				        new Coffee("Cafe Ganador"),
				        new Coffee("Cafe Lareno"), 
				        new Coffee("Cafe Tres Pontas"))
				);
	}

	@GetMapping("")
	Iterable<Coffee> getCoffees() {
		return coffeeRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Coffee> findById(@PathVariable String id) {
		return coffeeRepository.findById(id);
	}

	@PostMapping("")
	Coffee postCoffee(@RequestBody Coffee coffee) {

		return coffeeRepository.save(coffee);
	}

	@PutMapping("/{id}")
	ResponseEntity<Coffee> putCoffe(@PathVariable String id, @RequestBody Coffee newCoffee) {

		return !coffeeRepository.existsById(id) ? ResponseEntity.status(HttpStatus.CREATED).body(postCoffee(newCoffee))
				: ResponseEntity.status(HttpStatus.CREATED).body(newCoffee);
	}

	void deleteCoffee(@PathVariable String id) {
		coffeeRepository.deleteById(id);
	}
}
