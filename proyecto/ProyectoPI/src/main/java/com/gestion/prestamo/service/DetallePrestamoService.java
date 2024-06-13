package com.gestion.prestamo.service;

import com.gestion.prestamo.entity.DetallePrestamo;

public interface DetallePrestamoService {

    public abstract void registrar(DetallePrestamo detalle);

    public abstract DetallePrestamo buscarbyId(Integer cod);
}
