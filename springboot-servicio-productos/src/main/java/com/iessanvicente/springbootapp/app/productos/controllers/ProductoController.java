package com.iessanvicente.springbootapp.app.productos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.springbootapp.app.productos.models.entity.Producto;
import com.iessanvicente.springbootapp.app.productos.models.services.IProductoService;

@RestController
public class ProductoController {
	@Autowired
	private IProductoService service;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws InterruptedException{
		Producto p = service.findById(id);
		if(p == null) {
			return ResponseEntity.notFound().build();	
		}
		
		Thread.sleep(2000L);
		return ResponseEntity.ok(p);
		
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Producto producto){
		return ResponseEntity.ok(service.save(producto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id){
		if(!service.exists(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(service.save(producto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!service.exists(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
