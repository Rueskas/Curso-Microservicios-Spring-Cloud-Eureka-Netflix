package com.iessanvicente.springbootapp.app.items.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.springbootapp.app.items.models.entities.Item;
import com.iessanvicente.springbootapp.app.items.models.entities.services.ItemServiceImpl;

@RestController
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemServiceImpl service;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}/{cantidad}")
	public ResponseEntity<?> findById(@PathVariable Long id, @PathVariable int cantidad){
		Item item = service.findById(id, cantidad);
		if(item == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(item);
	}
}
