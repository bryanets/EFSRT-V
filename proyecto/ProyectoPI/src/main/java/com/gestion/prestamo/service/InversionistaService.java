package com.gestion.prestamo.service;

import java.util.List;

import com.gestion.prestamo.entity.JefePrestamista;

public interface InversionistaService {
    public abstract List<JefePrestamista> jefePrestamistas();

    public abstract JefePrestamista buscarPorId(int cod);

    public abstract void guardar(JefePrestamista jefePrestamista, Integer idGrupo);

    public abstract void Eliminar(int cod);

    public abstract Integer validarCorreo(String correo);

    public abstract Integer validarUsuario(String usuario);

    public abstract Integer validarDni(String dni);

    public abstract Integer validarTelefono(String telefono);
}