package com.gestion.prestamo.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.service.UserServices;

@Service
public class SecurityLogin implements UserDetailsService {
	@Autowired
	private UserServices usu;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails bean = null;
		Usuario u = usu.sesionUsuario(username);
		// rol del usario
		Set<GrantedAuthority> rol = new HashSet<GrantedAuthority>();
		rol.add(new SimpleGrantedAuthority(u.getRol().getDescripcion()));
		// crear bean
		bean = new User(username, u.getPassword(), rol);

		return bean;
	}

}
