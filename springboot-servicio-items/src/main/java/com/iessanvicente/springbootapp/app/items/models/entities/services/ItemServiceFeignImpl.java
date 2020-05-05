package com.iessanvicente.springbootapp.app.items.models.entities.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iessanvicente.springbootapp.app.items.clients.ProductoClienteRest;
import com.iessanvicente.springbootapp.app.items.models.entities.Item;
import com.iessanvicente.springbootapp.app.items.models.entities.Producto;

@Primary
@Service
public class ItemServiceFeignImpl implements IItemService {

	@Autowired
	private ProductoClienteRest feignClient;


	@Override
	public List<Item> findAll() {
		List<Producto> productos = feignClient.findAll();
		return productos.stream()
				.map(producto -> new Item(producto, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, int cantidad) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Producto producto = feignClient.findById(id);
		return new Item(producto, cantidad);
		
	}
}
