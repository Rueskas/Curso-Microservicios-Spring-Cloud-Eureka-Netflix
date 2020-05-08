package com.iessanvicente.springbootapp.app.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iessanvicente.springbootapp.app.common.models.entities.Producto;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {
	@GetMapping("/productos")
	public List<Producto> findAll();
	
	@GetMapping("/productos/{id}")
	public Producto findById(@PathVariable Long id);
	
	@PostMapping
	public Producto save(@RequestBody Producto producto);
	
	@PutMapping("/{id}")
	public Producto update(@RequestBody Producto producto, @PathVariable Long id);
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id);
}
