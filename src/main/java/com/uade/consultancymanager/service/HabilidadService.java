package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Habilidad;
import com.uade.consultancymanager.repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HabilidadService {

    private final HabilidadRepository habilidadRepository;

    @Autowired
    public HabilidadService(HabilidadRepository habilidadRepository) {
        this.habilidadRepository = habilidadRepository;
    }

    // Método para crear una nueva habilidad
    public Habilidad crearHabilidad(Habilidad habilidad) {
        if (habilidadRepository.existsByNombreHabilidad(habilidad.getNombreHabilidad())) {
            throw new IllegalArgumentException("Ya existe una habilidad con el mismo nombre.");
        }
        return habilidadRepository.save(habilidad);
    }

    // Método para obtener una habilidad por ID
    public Habilidad obtenerHabilidadPorId(int idHabilidad) {
        return habilidadRepository.findById(idHabilidad).orElse(null);
    }

    // Método para obtener todas las habilidades
    public List<Habilidad> obtenerTodasHabilidades() {
        return habilidadRepository.findAll();
    }

    // Método para actualizar una habilidad por ID
    public Habilidad actualizarHabilidad(int idHabilidad, Habilidad habilidadActualizada) {
        Habilidad habilidadExistente = habilidadRepository.findById(idHabilidad).orElse(null);
        if (habilidadExistente != null) {
            habilidadExistente.setNombreHabilidad(habilidadActualizada.getNombreHabilidad());
            return habilidadRepository.save(habilidadExistente);
        } else {
            return null;
        }
    }

    // Método para eliminar una habilidad por ID
    public boolean eliminarHabilidad(int idHabilidad) {
        Habilidad habilidadExistente = habilidadRepository.findById(idHabilidad).orElse(null);
        if (habilidadExistente != null) {
            habilidadRepository.delete(habilidadExistente);
            return true;
        } else {
            return false;
        }
    }
}
