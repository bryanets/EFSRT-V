package com.gestion.prestamo.service;

import java.util.List;

import com.gestion.prestamo.entity.Grupo;

public interface GrupoService {
    public abstract List<Grupo> grupos();

    public abstract void guardar(Grupo grupo);
}
