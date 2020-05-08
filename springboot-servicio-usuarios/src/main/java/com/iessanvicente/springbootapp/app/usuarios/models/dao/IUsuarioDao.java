package com.iessanvicente.springbootapp.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Usuario;

@RepositoryRestResource(path="usuarios")
public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	@RestResource(path="buscar-username")
	public Usuario findByUsername(String username);
}
