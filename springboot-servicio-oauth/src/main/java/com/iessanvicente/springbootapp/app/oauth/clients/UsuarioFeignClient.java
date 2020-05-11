package com.iessanvicente.springbootapp.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Usuario;

@FeignClient("servicio-usuarios")
public interface UsuarioFeignClient {
	
	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(String username);
	
	@PutMapping("/usuarios/{id}")
	public Usuario updateIntentos(@RequestBody Usuario usuario, @PathVariable Long id);
}
