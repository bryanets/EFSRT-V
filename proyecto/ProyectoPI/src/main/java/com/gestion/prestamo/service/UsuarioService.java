package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Enlace;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public Usuario sesionDelUsuario(String uLogin) {
		return repo.iniciarSesion(uLogin);
	}

	public List<Enlace> enlacesDelUsuario(String desRol) {
		return repo.traerEnlacesUsuario(desRol);
	}

	public Usuario save(Usuario u) {
		return repo.save(u);
	}

	public void update(Usuario u) {
		repo.save(u);
	}

	public void deleteById(Integer cod) {
		repo.deleteById(cod);
	}

	public Usuario findById(Integer cod) {
		return repo.findById(cod).orElse(null);
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Integer validarCorreo(String correo) {
		return repo.validarCorreo(correo);
	}

	public Integer validarUsuario(String correo) {
		return repo.validarUsuario(correo);
	}

	public Integer validarDni(String dni) {
		return repo.validarDni(dni);
	}

	public Integer validarTelefono(String telefono) {
		return repo.validarTelefono(telefono);
	}
}
