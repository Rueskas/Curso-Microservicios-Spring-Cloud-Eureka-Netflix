package com.iessanvicente.springbootapp.app.items.models.entities.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.iessanvicente.springbootapp.app.common.models.entities.Producto;
import com.iessanvicente.springbootapp.app.items.clients.ProductoClienteRest;
import com.iessanvicente.springbootapp.app.items.models.entities.Item;

@Primary
@Service
public class ItemServiceFeignImpl implements IItemService {

	@Autowired
	private ProductoClienteRest feignClient;


	@Override
	public List<Item> findAll() {
		List<Producto> productos = feignClient.findAll();
		System.out.println(productos);
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

	@Override
	public Producto save(Producto producto) {
		return feignClient.save(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return feignClient.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		feignClient.delete(id);
		
	}
}
