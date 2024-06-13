package com.gestion.prestamo.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.gestion.prestamo.entity.DetallePrestamo;
import com.gestion.prestamo.entity.Modalidad;
import com.gestion.prestamo.entity.Prestamo;
import com.gestion.prestamo.entity.Prestatario;
import com.gestion.prestamo.entity.Rol;
import com.gestion.prestamo.entity.Solicitud;
import com.gestion.prestamo.entity.Usuario;
import com.gestion.prestamo.service.DetallePrestamoService;
import com.gestion.prestamo.service.PrestamistaService;
import com.gestion.prestamo.service.PrestamoService;
import com.gestion.prestamo.service.PrestarioService;
import com.gestion.prestamo.service.SolicitudService;
import com.gestion.prestamo.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/prestamista")
public class PrestamistaController {

    @Autowired
    private PrestamistaService prestamistaService;

    @Autowired
    private PrestarioService prestatarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder encriptar;

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private DetallePrestamoService detallePrestamoService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {

        Usuario usuario = (Usuario) request.getSession().getAttribute("USU");

        model.addAttribute("listaPrestatarios",
                prestamistaService
                        .listaPrestatarios(usuario.getPrestamista().getLocalComercial().getIdLocalComercial()));
        return "prestamista";
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
            HttpServletRequest request) {

        Usuario prestamista = (Usuario) request.getSession().getAttribute("USU");
        Prestatario prestatario = new Prestatario();
        Usuario usu = new Usuario();

        Rol r = new Rol();
        r.setCodigo(5);

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
            prestatario = prestatarioService.buscarPorId(cod);
            usu.setIdUsuario(prestatario.getUsuario().getIdUsuario());

        }

        prestatario.setUsuario(usu);
        prestatario.setLocalComercial(prestamista.getPrestamista().getLocalComercial());

        prestatarioService.registrarPrestatario(prestatario);

        return "redirect:/prestamista/";
    }

    @PostMapping("/registrarPrestamo")
    public String registrarPrestamo(
            @RequestParam("solicitud") Integer codSolicitud,
            @RequestParam("modalidad") Integer codModalidad,
            HttpServletRequest request) {
        Prestamo prestamo = new Prestamo();

        Solicitud solicitud = new Solicitud();

        solicitud = solicitudService.buscarById(codSolicitud);

        Modalidad modalidad = new Modalidad();
        modalidad.setIdModalidad(codModalidad);

        Date fechaInicio = new Date();
        Integer cuotas = solicitud.getMonto().getDias();
        Double pagoDiario = solicitud.getMonto().getMontoInteres() / cuotas;
        DecimalFormat df = new DecimalFormat("#.##");
        pagoDiario = Double.valueOf(df.format(pagoDiario));

        prestamo.setCuotas(cuotas);
        prestamo.setPagoDiario(pagoDiario);
        prestamo.setEstado("activo");
        prestamo.setPrestatario(solicitud.getUsuario().getPrestatario());
        prestamo.setMontos(solicitud.getMonto());
        prestamo.setModalidad(modalidad);

        List<DetallePrestamo> listDetallePrestamos = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Avanzar un día desde la fecha de inicio

        prestamo.setFechai(calendar.getTime());

        for (int i = 0; i < cuotas; i++) {
            DetallePrestamo detallePrestamo = new DetallePrestamo();
            // Avanzar al siguiente día hábil (saltar sábados y domingos)
            while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            detallePrestamo.setFechaPago(calendar.getTime());
            detallePrestamo.setMonto(pagoDiario);
            detallePrestamo.setEstado("pendiente");

            listDetallePrestamos.add(detallePrestamo);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        if (codModalidad == 2) {
            listDetallePrestamos.clear();
            DetallePrestamo dPrestamoYape = new DetallePrestamo();
            dPrestamoYape.setFechaPago(calendar.getTime());
            dPrestamoYape.setMonto(solicitud.getMonto().getMontoInteres());
            dPrestamoYape.setEstado("pendiente");
            listDetallePrestamos.add(dPrestamoYape);
        }

        prestamo.setFechaf(calendar.getTime());
        prestamo.setDetallePrestamo(listDetallePrestamos);

        try {
            prestamoService.registrarPrestamo(prestamo, codSolicitud);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "redirect:/prestamista/";
    }

    @GetMapping("/eliminarPorId")
    public String eliminarPrestatario(@RequestParam("idEliminar") int cod) {
        try {
            Prestatario prestatario = new Prestatario();
            prestatario = prestatarioService.buscarPorId(cod);
            Integer j = prestatario.getUsuario().getIdUsuario();

            prestatarioService.eliminar(cod);
            usuarioService.deleteById(j);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/inversionista/";
    }

    @ResponseBody
    @RequestMapping("/solicitudes")
    public List<Solicitud> solicitudes(HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("USU");
        return prestamistaService.listaSolicitudes(usuario.getPrestamista().getLocalComercial().getIdLocalComercial());
    }

    @ResponseBody
    @RequestMapping("/solicitudesXUsuario")
    public List<Solicitud> solicitudesXUsuario(HttpServletRequest request,
            @RequestParam("apellidos") String apellidos) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("USU");
        return prestamistaService.listaSolicitudesXUsuario(
                usuario.getPrestamista().getLocalComercial().getIdLocalComercial(), apellidos);
    }

    @ResponseBody
    @RequestMapping("/solicitudesXFecha")
    public List<Solicitud> solicitudesXFecha(HttpServletRequest request,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("USU");
        Date fechaDesde = convertirStringADate(desde);
        Date fechaHasta = convertirStringADate(hasta);
        List<Solicitud> list = prestamistaService.listaSolicitudesXFecha(
                usuario.getPrestamista().getLocalComercial().getIdLocalComercial(), apellidos, fechaDesde, fechaHasta);
        for (Solicitud solicitud : list) {
            print("Fecha de solicitud: " + solicitud.getFecha());
        }

        return prestamistaService.listaSolicitudesXFecha(
                usuario.getPrestamista().getLocalComercial().getIdLocalComercial(), apellidos, fechaDesde, fechaHasta);
    }

    @ResponseBody
    @RequestMapping("/solicitudesXFechaSinPrestatario")
    public List<Solicitud> solicitudesXFechaSinPrestatario(HttpServletRequest request,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("USU");
        Date fechaDesde = convertirStringADate(desde);
        Date fechaHasta = convertirStringADate(hasta);
        return prestamistaService.listaSolicitudesXFechaSinPrestatario(
                usuario.getPrestamista().getLocalComercial().getIdLocalComercial(), fechaDesde, fechaHasta);
    }

    @GetMapping("/rechazar")
    public String rechazarSolicitud(@RequestParam("idSolicitud") int cod) {

        try {
            Solicitud solicitud = new Solicitud();
            solicitud = solicitudService.buscarById(cod);
            solicitud.setEstado("rechazado");
            solicitudService.guardar(solicitud);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/prestamista/";
    }

    @GetMapping("/registrarPago")
    public String registrarPago(@RequestParam("idDetalle") int cod) {
        try {
            DetallePrestamo detallePrestamo = new DetallePrestamo();
            detallePrestamo = detallePrestamoService.buscarbyId(cod);
            detallePrestamo.setEstado("pagado");
            detallePrestamo.setFechaPagada(new Date());
            detallePrestamoService.registrar(detallePrestamo);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/prestamista/";
    }

    @ResponseBody
    @RequestMapping("/buscarPorId")
    public Prestatario buscarPorId(@RequestParam("idPrestatario") int cod) {
        try {
            print("Buscando prestario con ID: " + cod);
            Prestatario prestatario = prestatarioService.buscarPorId(cod);
            print("Prestatario encontrado: " + prestatario.getUsuario().getUsuario());
            return prestatario;
        } catch (Exception e) {
            print("Error al buscar prestatario con ID " + cod + ": " + e.getMessage());
            print("Stack trace:");
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/validarCorreo")
    public Integer validarCorreo(@RequestParam("correo") String correo) {
        return usuarioService.validarCorreo(correo);
    }

    @ResponseBody
    @RequestMapping("/validarUsuario")
    public Integer validarUsuario(@RequestParam("usuario") String usuario) {
        return usuarioService.validarUsuario(usuario);
    }

    @ResponseBody
    @RequestMapping("/validarDni")
    public Integer validarDni(@RequestParam("dni") String dni) {
        return usuarioService.validarDni(dni);
    }

    @ResponseBody
    @RequestMapping("/validarTelefono")
    public Integer validarTelefono(@RequestParam("telefono") String telefono) {
        return usuarioService.validarTelefono(telefono);
    }

    private Date convertirStringADate(String fechaString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void print(Object obj) {
        System.out.println(obj);
    }
}
