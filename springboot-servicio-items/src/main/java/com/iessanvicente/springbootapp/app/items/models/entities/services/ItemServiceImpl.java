package com.iessanvicente.springbootapp.app.items.models.entities.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iessanvicente.springbootapp.app.items.models.entities.Item;
import com.iessanvicente.springbootapp.app.items.models.entities.Producto;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired 
	private RestTemplate restTemplate;
	
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(restTemplate.getForObject("http://localhost:8001/productos",Producto[].class));
		return productos.stream()
				.map(producto -> new Item(producto, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, int cantidad) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Producto producto = restTemplate.getForObject("http://localhost:8001/productos/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
		
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		ResponseEntity<Producto> response = 
				restTemplate.exchange("http://localhost:8001/productos",HttpMethod.POST, body, Producto.class);
		return response.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		ResponseEntity<Producto> response = 
				restTemplate.exchange("http://localhost:8001/productos/{id}",HttpMethod.PUT, body, Producto.class, pathVariables);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		restTemplate.delete("http://localhost:8001/productos/{id}", pathVariables);
	}
	
	
}
