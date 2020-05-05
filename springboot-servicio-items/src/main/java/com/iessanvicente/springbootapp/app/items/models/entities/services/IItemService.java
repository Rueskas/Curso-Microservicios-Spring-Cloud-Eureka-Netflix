package com.iessanvicente.springbootapp.app.items.models.entities.services;

import java.util.List;

import com.iessanvicente.springbootapp.app.items.models.entities.Item;

public interface IItemService {
	public List<Item> findAll();
	public Item findById(Long id, int cantidad);
}
