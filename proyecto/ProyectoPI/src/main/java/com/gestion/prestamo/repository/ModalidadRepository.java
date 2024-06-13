package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.prestamo.entity.Montos;

public interface ModalidadRepository extends JpaRepository<Montos, Integer> {

}
