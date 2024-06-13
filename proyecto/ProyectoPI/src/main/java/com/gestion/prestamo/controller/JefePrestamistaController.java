package com.gestion.prestamo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.Inversionista;
import com.gestion.prestamo.entity.JefePrestamista;
import com.gestion.prestamo.entity.LocalComercial;
import com.gestion.prestamo.entity.Prestamista;
import com.gestion.prestamo.entity.Rol;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.service.GrupoService;
import com.gestion.prestamo.service.InversionistaService;
import com.gestion.prestamo.service.JefeService;
import com.gestion.prestamo.service.LocalComercialService;
import com.gestion.prestamo.service.UserServices;
import com.gestion.prestamo.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/jefe")
public class JefePrestamistaController {

    @Autowired
    private JefeService jef;
    
	@Autowired
	private UserServices usu;

    @Autowired
    private LocalComercialService loc;

    @Autowired
    private BCryptPasswordEncoder encriptar;

    @Autowired
    private GrupoService grupoService;

    @RequestMapping("/inicio")
    public String index(Model model,HttpServletRequest request) {
    	Grupo g = (Grupo) request.getSession().getAttribute("GRUPO");
    	List<Prestamista> lista = new ArrayList<Prestamista>();
    	List<LocalComercial> locales = new ArrayList<LocalComercial>();
    	
    	for (Prestamista p : jef.lista()) {
    		if (p.getGrupo().getIdGrupo() == g.getIdGrupo()) {
    			lista.add(p);
    		}
    	}
    	
    	for (LocalComercial l : loc.lista()) {
    		if (l.getGrupo().getIdGrupo() == g.getIdGrupo()) {
    			locales.add(l);
    		}
    	}
    	
        model.addAttribute("lista", lista);
        model.addAttribute("locales", locales);
        return "jefePrestamistas";
    }

    @ResponseBody
    @RequestMapping("/buscarPorId")
    public Prestamista buscarPorId(@RequestParam("idJefePrestamista") int cod) {
        /* System.out.println(inversionistaService.buscarPorId(cod)); */
        return jef.buscarPorId(cod);
    }

    @RequestMapping("/guardar")
    public String procesarFormulario(
            @RequestParam("codigo") Integer cod,
            @RequestParam("nombres") String nombres,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("correo") String correo,
            @RequestParam("usuario") String usuario,
            @RequestParam("dni") String dni,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") String telefono,
            @RequestParam("local") Integer local) {
        Prestamista pres = new Prestamista();
        Usuario u = new Usuario();
        Rol r = new Rol();
        r.setCodigo(4);
        u.setRol(r);
        u.setUsuario(usuario);
        u.setNombres(nombres);
        u.setApellidos(apellidos);
        u.setDireccion(direccion);
        u.setDni(dni);
        u.setTelefono(telefono);
        u.setCorreo(correo);
        u.setPassword(encriptar.encode("prest"));

        LocalComercial l = new LocalComercial();
        l.setIdLocalComercial(local);
        pres.setLocalComercial(l);
        pres.setGrupo(loc.buscarPorId(local).getGrupo());
        
		if (cod == 0) {
			usu.registrar(u);
			pres.setUsuario(u);
			jef.guardar(pres);
		}
		else {
			u.setIdUsuario(jef.buscarPorId(cod).getUsuario().getIdUsuario());
			usu.registrar(u);
			pres.setIdPrestamista(cod);
			pres.setUsuario(u);
			jef.guardar(pres);	
		}

        return "redirect:/jefe/inicio";
    }
    
    @RequestMapping("/eliminarPorId")
    public String eliminarTrabajador(@RequestParam("idEliminar") int cod) {
    	jef.Eliminar(cod);
    	return "redirect:/jefe/inicio";
    }

    @ResponseBody
    @RequestMapping("/grupos")
    public List<Grupo> grupos() {
        return grupoService.grupos();
    }
    
    @ResponseBody
    @RequestMapping("/locales")
    public List<LocalComercial> locales() {
        return loc.lista();
    }
}