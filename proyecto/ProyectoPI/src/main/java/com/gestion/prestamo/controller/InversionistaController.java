package com.gestion.prestamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.Inversionista;
import com.gestion.prestamo.entity.JefePrestamista;
import com.gestion.prestamo.entity.Rol;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.service.GrupoService;
import com.gestion.prestamo.service.InversionistaService;
import com.gestion.prestamo.service.UsuarioService;

@Controller
@RequestMapping("/inversionista")
public class InversionistaController {

    @Autowired
    private InversionistaService inversionistaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder encriptar;

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaJefePrestamistas", inversionistaService.jefePrestamistas());
        return "inversionista";
    }

    @ResponseBody
    @RequestMapping("/buscarPorId")
    public JefePrestamista buscarPorId(@RequestParam("idJefePrestamista") int cod) {
        /* System.out.println(inversionistaService.buscarPorId(cod)); */
        return inversionistaService.buscarPorId(cod);
    }

    @PostMapping("/guardar")
    public String procesarFormulario(
            @RequestParam("codigo") Integer cod,
            @RequestParam("nombres") String nombres,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("correo") String correo,
            @RequestParam("usuario") String usuario,
            @RequestParam("dni") String dni,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") String telefono,
            @RequestParam("grupoDescripcion") Integer grupoDescripcion) {
        Inversionista inversionista = new Inversionista();
        JefePrestamista jPrestamista = new JefePrestamista();
        Usuario usu = new Usuario();
        Integer idGrupoAnterior = null;
        Rol r = new Rol();
        r.setCodigo(3);
        usu.setRol(r);
        usu.setUsuario(usuario);
        usu.setNombres(nombres);
        usu.setApellidos(apellidos);
        usu.setDireccion(direccion);
        usu.setDni(dni);
        usu.setTelefono(telefono);
        usu.setCorreo(correo);
        usu.setPassword(encriptar.encode("admin"));

        if (cod != 0) {
            jPrestamista = inversionistaService.buscarPorId(cod);
            usu.setIdUsuario(jPrestamista.getUsuario().getIdUsuario());
            // idGrupoAnterior = jPrestamista.getGrupo().getIdGrupo();
            idGrupoAnterior = jPrestamista.getGrupo() != null ? jPrestamista.getGrupo().getIdGrupo() : null;
        }

        jPrestamista.setUsuario(usu);
        inversionista.setIdInversionista(1);
        jPrestamista.setInversionista(inversionista);

        if (grupoDescripcion != 1000) {
            Grupo grupo = new Grupo();
            grupo.setIdGrupo(grupoDescripcion);
            jPrestamista.setGrupo(grupo);
        }

        inversionistaService.guardar(jPrestamista, idGrupoAnterior);

        return "redirect:/inversionista/";
    }

    @GetMapping("/eliminarPorId")
    public String eliminarTrabajador(@RequestParam("idEliminar") int cod) {
        try {
            JefePrestamista jefePrestamista = new JefePrestamista();
            jefePrestamista = inversionistaService.buscarPorId(cod);
            Integer j = jefePrestamista.getUsuario().getIdUsuario();
            Grupo grupo = new Grupo();
            if (jefePrestamista.getGrupo() != null) {
                grupo = jefePrestamista.getGrupo();
                grupo.setEstado("disponible");
                grupoService.guardar(grupo);
            }

            inversionistaService.Eliminar(cod);
            usuarioService.deleteById(j);
            System.err.println("***********delete*********");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("***********otro ----- delete*********");
        return "redirect:/inversionista/";
    }

    @ResponseBody
    @RequestMapping("/grupos")
    public List<Grupo> grupos() {
        return grupoService.grupos();
    }

    @ResponseBody
    @RequestMapping("/validarCorreo")
    public Integer validarCorreo(@RequestParam("correo") String correo) {
        return inversionistaService.validarCorreo(correo);
    }

    @ResponseBody
    @RequestMapping("/validarUsuario")
    public Integer validarUsuario(@RequestParam("usuario") String usuario) {
        return inversionistaService.validarUsuario(usuario);
    }

    @ResponseBody
    @RequestMapping("/validarDni")
    public Integer validarDni(@RequestParam("dni") String dni) {
        return inversionistaService.validarDni(dni);
    }

    @ResponseBody
    @RequestMapping("/validarTelefono")
    public Integer validarTelefono(@RequestParam("telefono") String telefono) {
        return inversionistaService.validarTelefono(telefono);
    }
}