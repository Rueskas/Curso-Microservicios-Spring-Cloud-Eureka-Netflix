package com.iessanvicente.springbootapp.app.items.models.entities.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
}
