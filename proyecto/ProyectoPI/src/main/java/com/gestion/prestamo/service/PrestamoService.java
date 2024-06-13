package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.prestamo.entity.DetallePrestamo;
import com.gestion.prestamo.entity.Prestamo;
import com.gestion.prestamo.entity.Solicitud;
import com.gestion.prestamo.repository.DetallePrestamoRepository;
import com.gestion.prestamo.repository.PrestamoRepository;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository repository;

    @Autowired
    private DetallePrestamoRepository detallePrestamoRepository;

    @Autowired
    private SolicitudService solicitudService;

    public List<Prestamo> lista() {
        return repository.findAll();
    }

    public void guardar(Prestamo p) {
        repository.save(p);
    }

    @Transactional
    public void registrarPrestamo(Prestamo p, int codSolicitud) {
        List<DetallePrestamo> listDetalle = p.getDetallePrestamo();
        p = repository.save(p);

        for (DetallePrestamo detallePrestamo : listDetalle) {
            detallePrestamo.setPrestamo(p);
            detallePrestamoRepository.save(detallePrestamo);
        }
        Solicitud solicitud = solicitudService.buscarById(codSolicitud);
        solicitud.setEstado("aprobado");
        solicitudService.guardar(solicitud);
    }
}
