package com.gestion.prestamo.service;

import java.util.Date;
import java.util.List;

import com.gestion.prestamo.entity.Prestatario;
import com.gestion.prestamo.entity.Solicitud;

public interface PrestamistaService {
    public abstract List<Prestatario> listaPrestatarios(int codigo);

    public abstract Prestatario buscarPorId(int cod);

    public abstract void guardar(Prestatario prestatario);

    public abstract void Eliminar(int cod);

    public abstract List<Solicitud> listaSolicitudes(int cod);

    public abstract List<Solicitud> listaSolicitudesXUsuario(int cod, String apellidos);

    public abstract List<Solicitud> listaSolicitudesXFecha(int cod, String apellidos, Date desde, Date hasta);

    public abstract List<Solicitud> listaSolicitudesXFechaSinPrestatario(int cod, Date desde, Date hasta);

    public abstract Integer validarCorreo(String correo);

    public abstract Integer validarUsuario(String usuario);

    public abstract Integer validarDni(String dni);

    public abstract Integer validarTelefono(String telefono);
}
