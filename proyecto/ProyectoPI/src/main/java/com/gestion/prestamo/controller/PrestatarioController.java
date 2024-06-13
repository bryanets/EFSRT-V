package com.gestion.prestamo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gestion.prestamo.entity.Montos;
import com.gestion.prestamo.entity.Prestamo;
import com.gestion.prestamo.entity.Prestatario;
import com.gestion.prestamo.entity.Solicitud;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.service.JefeService;
import com.gestion.prestamo.service.LocalComercialService;
import com.gestion.prestamo.service.ModalidadService;
import com.gestion.prestamo.service.PrestamoService;
import com.gestion.prestamo.service.SolicitudService;
import com.gestion.prestamo.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/prestatario")
public class PrestatarioController {

    @Autowired
    private JefeService jef;

    @Autowired
    private UserServices usu;

    @Autowired
    private LocalComercialService loc;

    @Autowired
    private ModalidadService mod;

    @Autowired
    private PrestamoService prest;

    @Autowired
    private SolicitudService solicitudService;

    @RequestMapping("/inicio")
    public String index(Model model, HttpServletRequest request) {
        List<Prestamo> lp = new ArrayList<Prestamo>();
        Prestatario pres = (Prestatario) request.getSession().getAttribute("PRESTARIO");

        for (Prestamo p : prest.lista()) {
            if (p.getPrestatario().getIdPrestatario() == pres.getIdPrestatario()) {
                lp.add(p);
                System.out.println(p.getPrestatario().getUsuario().getNombres());
            }
        }
        model.addAttribute("listamod", mod.lista());
        model.addAttribute("lista", lp);
        return "vistaprestario";
    }

    @RequestMapping("/guardar")
    public String procesarFormulario(
            @RequestParam("codigo") Integer cod,
            @RequestParam("monto") Double mon,
            @RequestParam("fechai") Date fi,
            @RequestParam("fechaf") Date ff,
            @RequestParam("dias") Integer d,
            @RequestParam("pago") Double pa, HttpServletRequest request) {

        Prestamo p = new Prestamo();
        Montos m = new Montos();
        m.setIdMonto(cod);

        p.setMontos(m);
        p.setCuotas(d);
        p.setEstado("pendiente");
        p.setFechaf(ff);
        p.setFechai(fi);
        p.setPagoDiario(pa);
        Prestatario pres = (Prestatario) request.getSession().getAttribute("PRESTARIO");
        p.setPrestatario(pres);

        prest.guardar(p);
        Solicitud solicitud = new Solicitud();

        solicitud.setMonto(m);
        solicitud.setFecha(fi);
        solicitud.setEstado("pendiente");
        Usuario usuario = (Usuario) request.getSession().getAttribute("USU");
        solicitud.setUsuario(usuario);

        solicitudService.guardar(solicitud);

        return "redirect:/prestatario/inicio";
    }

}
