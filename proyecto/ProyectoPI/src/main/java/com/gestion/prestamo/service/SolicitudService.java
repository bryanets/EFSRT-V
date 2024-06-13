package com.gestion.prestamo.service;

import com.gestion.prestamo.entity.Solicitud;

public interface SolicitudService {
    public abstract void guardar(Solicitud solicitud);

    public abstract Solicitud buscarById(int cod);
}
