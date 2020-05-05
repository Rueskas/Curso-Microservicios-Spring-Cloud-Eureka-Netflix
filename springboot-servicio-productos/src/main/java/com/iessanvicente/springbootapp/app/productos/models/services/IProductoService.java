package com.iessanvicente.springbootapp.app.productos.models.services;

import java.util.List;

import com.iessanvicente.springbootapp.app.productos.models.entity.Producto;

public interface IProductoService {
	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);
	public boolean exists(Long id);

}
