package com.gestion.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.prestamo.entity.RolEnlace;
import com.gestion.prestamo.entity.RolEnlacePK;

public interface RolEnlaceRepository extends JpaRepository<RolEnlace, RolEnlacePK> {

}
