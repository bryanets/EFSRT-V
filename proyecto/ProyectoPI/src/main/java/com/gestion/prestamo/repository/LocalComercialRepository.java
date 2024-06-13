package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.prestamo.entity.Grupo;
import com.gestion.prestamo.entity.LocalComercial;

public interface LocalComercialRepository extends JpaRepository<LocalComercial, Integer> {

}
