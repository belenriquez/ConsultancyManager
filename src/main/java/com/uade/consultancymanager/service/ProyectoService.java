package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Proyectos;
import com.uade.consultancymanager.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProyectoService {
    @Autowired
    private ProyectoRepository projectRepository;

    // Método para crear un proyecto
    public Proyectos crearProyecto(Proyectos proyecto) {
        return projectRepository.save(proyecto);
    }

    // Método para obtener un proyecto por ID
    public Proyectos obtenerProyectoPorId(int idProyecto) {
        return projectRepository.findById(idProyecto).orElse(null);
    }

    // Método para actualizar un proyecto por ID
    public Proyectos actualizarProyecto(int idProyecto, Proyectos proyectoActualizado) {
        Proyectos proyectoExistente = projectRepository.findById(idProyecto).orElse(null);
        if (proyectoExistente != null) {
            proyectoExistente.setNombre(proyectoActualizado.getNombre());
            proyectoExistente.setDescripcion(proyectoActualizado.getDescripcion());
            proyectoExistente.setFechaInicio(proyectoActualizado.getFechaInicio());
            proyectoExistente.setFechaFin(proyectoActualizado.getFechaFin());
            proyectoExistente.setEstado(proyectoActualizado.getEstado());
            return projectRepository.save(proyectoExistente);
        } else {
            return null;
        }
    }

    // Método para eliminar un proyecto por ID
    public boolean eliminarProyecto(int idProyecto) {
        Proyectos proyectoExistente = projectRepository.findById(idProyecto).orElse(null);
        if (proyectoExistente != null) {
            projectRepository.delete(proyectoExistente);
            return true;
        } else {
            return false;
        }
    }
}
