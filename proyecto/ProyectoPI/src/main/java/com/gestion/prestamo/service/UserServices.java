package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Enlace;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.repository.UsuarioRepository;

@Service
public class UserServices {
	@Autowired
	private UsuarioRepository repo;

	public Usuario sesionUsuario(String nom) {
		return repo.iniciarSesion(nom);
	}

	/*
	 * public Usuario getUsuario(String nom, String psw) {
	 * return repo.traerUsuario(nom, psw);
	 * }
	 */

	public List<Enlace> enlacesDelUsuario(String rol) {
		return repo.traerEnlacesUsuario(rol);
	}

	public void registrar(Usuario u) {
		repo.save(u);
	}

	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}

	public Usuario findById(int cod) {
		return repo.findById(cod).orElse(null);
	}
}
