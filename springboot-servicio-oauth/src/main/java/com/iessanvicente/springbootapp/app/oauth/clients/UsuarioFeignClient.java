package com.iessanvicente.springbootapp.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Usuario;

@FeignClient("servicio-usuarios")
public interface UsuarioFeignClient {
	
	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(String username);
}
