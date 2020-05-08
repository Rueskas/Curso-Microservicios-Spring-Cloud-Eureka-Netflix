package com.iessanvicente.springbootapp.app.items.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.springbootapp.app.common.models.entities.Producto;
import com.iessanvicente.springbootapp.app.items.models.entities.Item;
import com.iessanvicente.springbootapp.app.items.models.entities.services.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemsController {
	@Autowired
	private IItemService service;
	
	@Autowired
	private Environment environment;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> config(@Value("${server.port}") String port){
		Map<String, Object> response = new HashMap<>();
		response.put("texto", texto);
		response.put("puerto", port);
		
		if(environment.getActiveProfiles().length > 0 &&
				environment.getActiveProfiles()[0].equals("dev")) {
			
			response.put("autor.nombre", environment.getProperty("configuracion.autor.nombre"));
			response.put("autor.email", environment.getProperty("configuracion.autor.email"));
		}
		
		return ResponseEntity.ok(response);
	}
	
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
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody Producto producto) {
		return ResponseEntity.ok(service.save(producto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> put(@RequestBody Producto producto, @PathVariable Long id) {
		return ResponseEntity.ok(service.save(producto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
