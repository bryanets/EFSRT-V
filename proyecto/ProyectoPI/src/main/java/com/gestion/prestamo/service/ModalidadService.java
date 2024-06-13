package com.gestion.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.prestamo.entity.Montos;
import com.gestion.prestamo.repository.ModalidadRepository;

@Service
public class ModalidadService {

    @Autowired
    private ModalidadRepository repository;

    public List<Montos> lista() {
        return repository.findAll();
    }

    public void guardar(Montos m) {
        repository.save(m);
    }

}
