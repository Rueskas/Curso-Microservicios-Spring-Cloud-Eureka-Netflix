package com.iessanvicente.springbootapp.app.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iessanvicente.springbootapp.app.items.models.entities.Producto;

@FeignClient(name="servicio-productos", url="localhost:8001")
public interface ProductoClienteRest {
	@GetMapping("/productos")
	public List<Producto> findAll();
	@GetMapping("/productos/{id}")
	public Producto findById(@PathVariable Long id);
}
