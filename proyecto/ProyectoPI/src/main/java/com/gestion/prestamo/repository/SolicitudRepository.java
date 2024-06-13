package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.prestamo.entity.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

}
