package com.example.endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffees")
public class RestApiDemoController {
	private List<Coffee>coffees=new ArrayList<>();

	public RestApiDemoController( ) {
		 coffees.addAll(List.of(new Coffee("cafe Cereze"),
				 		new Coffee("Cafe Ganador"),
				 		new Coffee("Cafe Lareno"),
				 		new Coffee("Cafe Tres Pontas")));	
	}
	
	@GetMapping("")
   Iterable<Coffee>getCoffees(){
	   return coffees;
   }
	
	@GetMapping("/{id}")
	Optional<Coffee>findById(@PathVariable String id){
			for(Coffee coffee:coffees) {
					if(coffee.getId().equals(id)) {
						return Optional.of(coffee);
					         }
					  }
		return Optional.empty();
	}
	@PostMapping("")
	Coffee postCoffee(@RequestBody Coffee coffee) {
			coffees.add(coffee);
			return coffee;
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Coffee>putCoffe(@PathVariable String id,
			@RequestBody Coffee newCoffee){
		 int coffeeIndex=-1;
		 for(Coffee coffee:coffees)  
			 if(coffee.getId().equals(id)) {
				 coffeeIndex=coffees.indexOf(coffee);
				 coffees.set(coffeeIndex, newCoffee);
			 }
				 	 
		return  coffeeIndex==-1?ResponseEntity.status(HttpStatus.CREATED).body(postCoffee(newCoffee)):
								ResponseEntity.status(HttpStatus.CREATED).body(newCoffee);
	}
}
