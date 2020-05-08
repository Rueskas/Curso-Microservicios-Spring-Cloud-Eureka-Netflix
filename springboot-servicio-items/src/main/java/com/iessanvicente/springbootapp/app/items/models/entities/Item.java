package com.iessanvicente.springbootapp.app.items.models.entities;

import com.iessanvicente.springbootapp.app.common.models.entities.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
	private Producto producto;
	private int cantidad;
	private Double getTotal() {
		return producto.getPrecio() * cantidad;
	}
}
