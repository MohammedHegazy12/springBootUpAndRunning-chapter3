package com.example.endpoints.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
 
@Entity
public class Coffee{
	 @Id
	 private   String id;
	 private String name;
	 
	 
	public Coffee() {
 
	}

	public Coffee(String id, String name) {
	 
		this.id = id;
		this.name = name;
	}
	
	public Coffee(String name) {
		this(UUID.randomUUID().toString(),name);
 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coffee [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}
	 
	  
 }