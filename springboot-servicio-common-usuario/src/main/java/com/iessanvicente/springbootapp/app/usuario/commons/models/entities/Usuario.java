package com.iessanvicente.springbootapp.app.usuario.commons.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true, length=20)
	private String username;
	private String password;
	private Boolean enabled;
	private String nombre;
	private String apellidos;
	@Column(unique=true, length=100)
	private String email;
	private Integer intentos;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="usuarios_roles",
		joinColumns=@JoinColumn(name="usuario_id"),
		inverseJoinColumns=@JoinColumn(name="role_id"),
		uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "role_id"})}
		)
	List<Role> roles = new ArrayList<>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5792262097640206865L;
	
}
