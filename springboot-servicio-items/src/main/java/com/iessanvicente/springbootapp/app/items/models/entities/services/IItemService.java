package com.iessanvicente.springbootapp.app.items.models.entities.services;

import java.util.List;

import com.iessanvicente.springbootapp.app.common.models.entities.Producto;
import com.iessanvicente.springbootapp.app.items.models.entities.Item;

public interface IItemService {
	public List<Item> findAll();
	public Item findById(Long id, int cantidad);
	public Producto save(Producto producto);
	public Producto update(Producto producto, Long id);
	public void delete(Long id);
	
}
