package com.gestion.prestamo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "detalleprestamo")
public class DetallePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle")
    private int idDetalle;

    @Column(name = "fechapago")
    private Date fechaPago;

    @Column(name = "fechapagada")
    private Date fechaPagada;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "estado")
    private String estado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idprestamo")
    private Prestamo prestamo;
}
