package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Habilidad;
import com.uade.consultancymanager.repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HabilidadService {

    @Autowired
    private HabilidadRepository habilidadRepository;

    // Método para crear una nueva habilidad
    public Habilidad crearHabilidad(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    // Método para obtener una habilidad por ID
    public Habilidad obtenerHabilidadPorId(int idHabilidad) {
        return habilidadRepository.findById(idHabilidad).orElse(null);
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
