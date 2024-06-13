package com.gestion.prestamo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gestion.prestamo.entity.Enlace;
import com.gestion.prestamo.entity.Prestatario;
import com.gestion.prestamo.entity.Rol;

import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.service.JefeService;
import com.gestion.prestamo.service.LocalComercialService;
import com.gestion.prestamo.service.ModalidadService;
import com.gestion.prestamo.service.PrestarioService;
import com.gestion.prestamo.service.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

@SessionAttributes({ "ENLACES", "USUARIO", "NOMBRE", "INICIAL", "USU" })
@Controller
@RequestMapping("/session")
public class UsuarioController {
	@Autowired
	private UserServices usu;

	@Autowired
	private JefeService jef;

	@Autowired
	private LocalComercialService loc;

	@Autowired
	private PrestarioService pres;

	@Autowired
	private BCryptPasswordEncoder encriptar;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/inversionista")
	public String inversionistas() {
		return "redirect:/inversionista/";
	}

	@RequestMapping("/vistaprestario")
	public String vistaprestario() {
		return "redirect:/vistaprestario/";
	}

	@RequestMapping("/prestamista")
	public String prestamistas() {
		return "redirect:/prestamista/";
	}

	@RequestMapping("/prestatario")
	public String prestatario() {
		return "redirect:/prestatario/";
	}

	@RequestMapping("/registro")
	public String registro(Model model, HttpServletRequest request) {
		model.addAttribute("locales", loc.lista());
		return "registrousuario";
	}

	@RequestMapping("/registrar")
	public String registrar(@RequestParam("apellido") String ape, @RequestParam("dni") String dni,
			@RequestParam("telefono") String tel,
			@RequestParam("direccion") String dir, @RequestParam("nombre") String nom,
			@RequestParam("username") String use,
			@RequestParam("correo") String cor, @RequestParam("password") String psw, @RequestParam("local") int lo) {
		Usuario u = new Usuario();
		u.setUsuario(use);
		u.setCorreo(cor);
		u.setDireccion(dir);
		u.setNombres(nom);
		u.setTelefono(tel);
		u.setDni(dni);
		u.setApellidos(ape);
		u.setPassword(encriptar.encode(psw));
		Rol rol = new Rol();
		rol.setCodigo(5);
		u.setRol(rol);

		usu.registrar(u);

		Prestatario p = new Prestatario();
		p.setLocalComercial(loc.buscarPorId(lo));
		p.setUsuario(u);
		pres.guardar(p);
		return "redirect:/session/login";
	}

	@RequestMapping("/intranet")
	public String intranet(Authentication auth, HttpServletRequest request) {
		String nomLogin = auth.getName();
		String rol = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		List<Enlace> lista = usu.enlacesDelUsuario(rol);

		Usuario bean = usu.sesionUsuario(nomLogin);

		for (Enlace e : lista) {
			System.out.println(e.getDescripcion());
		}
		Usuario u = usu.sesionUsuario(auth.getName());
		request.getSession().setAttribute("NOMBRE", u.getNombres() + " " + u.getApellidos());
		if (u.getRol().getCodigo() == 3) {
			request.getSession().setAttribute("GRUPO", jef.buscarPorUsu(u).getGrupo());
		}
		if (u.getRol().getCodigo() == 5) {
			request.getSession().setAttribute("PRESTARIO", pres.buscarPorUsu(u.getIdUsuario()));
		}
		request.getSession().setAttribute("INICIAL", u.getNombres().toUpperCase().charAt(0));
		request.getSession().setAttribute("USUARIO", u);
		request.getSession().setAttribute("ENLACES", lista);
		request.getSession().setAttribute("USU", bean);

		if (bean.getRol().getCodigo() == 2) {
			return "redirect:/inversionista/";
		}
		if (bean.getRol().getCodigo() == 3) {
			return "redirect:/jefe/inicio";
		}
		if (bean.getRol().getCodigo() == 4) {
			return "redirect:/prestamista/";
		}
		if (bean.getRol().getCodigo() == 5) {
			return "redirect:/prestatario/inicio";
		}
		return "vistaprestario";
	}
}
