package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.prestamo.entity.Prestatario;

public interface PrestarioRepository extends JpaRepository<Prestatario, Integer> {

}
