package com.gestion.prestamo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestion.prestamo.entity.Rol;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.service.UsuarioService;

@SpringBootTest
class ProyectoPiApplicationTests {
	@Autowired
	private BCryptPasswordEncoder encriptar;

	@Autowired
	private UsuarioService usuService;

	@Test
	void contextLoads() {

		Usuario usu = new Usuario();
		/*
		 * Rol r = new Rol();
		 * 
		 * r.setCodigo(2);
		 * usu.setRol(r);
		 * usu.setUsuario("inversionista");
		 * usu.setNombres("Martin");
		 * usu.setApellidos("Vizcarra");
		 * usu.setDireccion("Independecia S/n ");
		 * usu.setDni("34657618");
		 * usu.setTelefono("900037814");
		 * usu.setCorreo("vizcarra@inversionista.com");
		 */
		usu = usuService.findById(18);
		usu.setPassword(encriptar.encode("admin"));
		usuService.save(usu);
	}

}
