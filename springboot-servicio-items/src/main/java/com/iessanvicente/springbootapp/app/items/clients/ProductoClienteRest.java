package com.iessanvicente.springbootapp.app.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iessanvicente.springbootapp.app.items.models.entities.Producto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {
	@GetMapping("/productos")
	public List<Producto> findAll();
	
	@GetMapping("/productos/{id}")
	public Producto findById(@PathVariable Long id);
	
}
