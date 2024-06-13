package com.gestion.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.prestamo.entity.Enlace;
import com.gestion.prestamo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("Select u from Usuario u where u.usuario = ?1")
	public Usuario iniciarSesion(String uLogin);

	@Query("Select e from RolEnlace re join re.enlace e where re.rol.descripcion = ?1")
	public List<Enlace> traerEnlacesUsuario(String desRol);

	@Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.correo = ?1")
	public Integer validarCorreo(String correo);

	@Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.usuario = ?1")
	public Integer validarUsuario(String usuario);

	@Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.dni = ?1")
	public Integer validarDni(String dni);

	@Query("SELECT COUNT(x) FROM JefePrestamista x WHERE x.usuario.telefono = ?1")
	public Integer validarTelefono(String telefono);
}
