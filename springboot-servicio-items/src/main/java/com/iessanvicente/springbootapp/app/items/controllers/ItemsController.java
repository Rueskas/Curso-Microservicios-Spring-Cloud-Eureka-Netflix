package com.iessanvicente.springbootapp.app.items.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.springbootapp.app.items.models.entities.Item;
import com.iessanvicente.springbootapp.app.items.models.entities.Producto;
import com.iessanvicente.springbootapp.app.items.models.entities.services.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemsController {
	@Autowired
	private IItemService service;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@HystrixCommand(fallbackMethod="metodoAlternativo")
	@GetMapping("/{id}/{cantidad}")
	public ResponseEntity<?> findById(@PathVariable Long id, @PathVariable int cantidad){
		Item item = service.findById(id, cantidad);
		if(item == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(item);
	}
	
	public ResponseEntity<?> metodoAlternativo(Long id, int cantidad){
		Producto producto = new Producto();
		producto.setId(id);
		
		Item item = new Item(producto, cantidad);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "No se ha podido conectar al servicio-productos");
		response.put("Item", item);
		return ResponseEntity.ok(response);
	}
}
