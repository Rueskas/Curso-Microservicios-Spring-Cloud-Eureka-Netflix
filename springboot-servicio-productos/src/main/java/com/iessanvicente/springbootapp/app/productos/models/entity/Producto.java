package com.iessanvicente.springbootapp.app.productos.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="PRODUCTOS")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private double precio;
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	private Date createAt;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 662953643562924596L;
	
}
