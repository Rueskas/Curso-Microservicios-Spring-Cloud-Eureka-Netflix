package com.iessanvicente.springbootapp.app.items.models.entities;

import java.util.Date;

import lombok.Data;

@Data
public class Producto {
	private Long id;
	private Double precio;
	private String nombre;
	private Date createAt;
}
