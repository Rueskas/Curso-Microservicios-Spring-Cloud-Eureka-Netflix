package com.iessanvicente.springbootapp.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Role;
import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Role.class);
	}
}
