package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

}
