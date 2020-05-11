package com.iessanvicente.springbootapp.app.oauth.services;

import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);

	public Usuario updateIntentos(Usuario usuario, Long id);
}
