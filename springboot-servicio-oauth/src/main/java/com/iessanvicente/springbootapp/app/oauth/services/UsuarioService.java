package com.iessanvicente.springbootapp.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iessanvicente.springbootapp.app.oauth.clients.UsuarioFeignClient;
import com.iessanvicente.springbootapp.app.usuario.commons.models.entities.Usuario;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {
	
	@Autowired
	private UsuarioFeignClient feignClient;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usu = feignClient.findByUsername(username);
		if(usu == null) {
			throw new UsernameNotFoundException("Usuario no existe");
		}
		
		List<GrantedAuthority> roles = usu.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.collect(Collectors.toList());
		return new User(usu.getUsername(), usu.getPassword(), usu.getEnabled(),
				true, true, true, roles);
	}
	

	@Override
	public Usuario findByUsername(String username) {
		return feignClient.findByUsername(username);
	}
	
	
}
