package com.iessanvicente.springbootapp.app.productos.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iessanvicente.springbootapp.app.productos.models.entity.Producto;
import com.iessanvicente.springbootapp.app.productos.models.repositories.ProductoDao;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private ProductoDao repository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return repository.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public boolean exists(Long id) {
		return repository.existsById(id);
	}
}
