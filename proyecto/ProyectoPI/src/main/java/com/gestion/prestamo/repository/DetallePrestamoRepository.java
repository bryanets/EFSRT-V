package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.prestamo.entity.DetallePrestamo;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, Integer> {

}
