package com.iessanvicente.springbootapp.app.oauth.security.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.iessanvicente.springbootapp.app.oauth.services.IUsuarioService;
import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Usuario;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {
	
	@Autowired
	private IUsuarioService service;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
	
		UserDetails user = (UserDetails) authentication.getPrincipal();
		Usuario usuario = null;
		usuario = service.findByUsername(authentication.getName());
		if(usuario.getIntentos() == null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);	
		}
		service.updateIntentos(usuario, usuario.getId());
		System.out.println("Success Login: " + user.getUsername());
	}
	
	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		System.out.println("Error Login");
		Usuario usuario = null;
		try {
			usuario = service.findByUsername(authentication.getName());
		} catch(FeignException e) {
			System.out.println("El usuario no existe en el sistema");
		}
		
		if(usuario.getIntentos() == null) {
			usuario.setIntentos(1);
		} else {
			usuario.setIntentos(usuario.getIntentos()+1);
		}
		
		if(usuario.getIntentos() >= 3) {
			usuario.setEnabled(false);
		}
		
		service.updateIntentos(usuario, usuario.getId());
	}
}
