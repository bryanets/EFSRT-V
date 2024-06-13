package com.gestion.prestamo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "modalidad")
public class Modalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodalidad")
    private int idModalidad;

    @Column(name = "descripcion")
    private String descripcion;
}
