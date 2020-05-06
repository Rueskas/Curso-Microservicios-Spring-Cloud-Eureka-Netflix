package com.iessanvicente.springbootapp.app.items.models.entities.services;

import java.util.List;

import com.iessanvicente.springbootapp.app.items.models.entities.Item;
import com.iessanvicente.springbootapp.app.items.models.entities.Producto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public interface IItemService {
	public List<Item> findAll();
	public Item findById(Long id, int cantidad);
	public Producto save(Producto producto);
	public Producto update(Producto producto, Long id);
	public void delete(Long id);
	
}
