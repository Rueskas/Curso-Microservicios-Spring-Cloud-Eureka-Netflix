package com.iessanvicente.springbootapp.app.productos.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessanvicente.springbootapp.app.productos.models.entity.Producto;

public interface ProductoDao extends JpaRepository<Producto, Long>{

}
