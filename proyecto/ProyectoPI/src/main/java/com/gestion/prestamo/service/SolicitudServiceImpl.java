package com.gestion.prestamo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Solicitud;
import com.gestion.prestamo.repository.SolicitudRepository;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    public void guardar(Solicitud solicitud) {
        solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud buscarById(int cod) {
        return solicitudRepository.findById(cod).orElse(null);
    }

}
