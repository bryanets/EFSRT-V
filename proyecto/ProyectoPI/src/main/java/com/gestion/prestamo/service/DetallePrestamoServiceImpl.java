package com.gestion.prestamo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.DetallePrestamo;
import com.gestion.prestamo.repository.DetallePrestamoRepository;

@Service
public class DetallePrestamoServiceImpl implements DetallePrestamoService {

    @Autowired
    private DetallePrestamoRepository detallePrestamoRepository;

    @Override
    public void registrar(DetallePrestamo detalle) {
        detallePrestamoRepository.save(detalle);
    }

    @Override
    public DetallePrestamo buscarbyId(Integer cod) {
        return detallePrestamoRepository.findById(cod).orElse(null);
    }

}
