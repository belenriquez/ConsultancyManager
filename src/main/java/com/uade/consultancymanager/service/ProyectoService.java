package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Asignaciones;
import com.uade.consultancymanager.entity.Proyectos;
import com.uade.consultancymanager.repository.AsignacionRepository;
import com.uade.consultancymanager.repository.EmpleadoProyectosRepository;
import com.uade.consultancymanager.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final EmpleadoProyectosRepository empleadoProyectoRepository;

    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository, EmpleadoProyectosRepository empleadoProyectoRepository) {
        this.proyectoRepository = proyectoRepository;
        this.empleadoProyectoRepository = empleadoProyectoRepository;
    }

    // Método para crear un proyecto
    public Proyectos crearProyecto(Proyectos proyecto) {
        // Validar si ya existe un proyecto con el mismo nombre
        if (proyectoRepository.existsByNombre(proyecto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un proyecto con el mismo nombre.");
        }
        return proyectoRepository.save(proyecto);
    }

    // Método para obtener todos los proyectos
    public List<Proyectos> obtenerTodosProyectos() {
        return proyectoRepository.findAll();
    }

    // Método para obtener un proyecto por ID
    public Proyectos obtenerProyectoPorId(int idProyecto) {
        Optional<Proyectos> proyecto = proyectoRepository.findById(idProyecto);
        return proyecto.orElse(null);
    }

    // Método para actualizar un proyecto por ID
    public Proyectos actualizarProyecto(int idProyecto, Proyectos proyectoActualizado) {
        Optional<Proyectos> optionalProyecto = proyectoRepository.findById(idProyecto);
        if (optionalProyecto.isPresent()) {
            Proyectos proyectoExistente = optionalProyecto.get();
            proyectoExistente.setNombre(proyectoActualizado.getNombre());
            proyectoExistente.setDescripcion(proyectoActualizado.getDescripcion());
            proyectoExistente.setFechaInicio(proyectoActualizado.getFechaInicio());
            proyectoExistente.setFechaFin(proyectoActualizado.getFechaFin());
            proyectoExistente.setEstado(proyectoActualizado.getEstado());
            return proyectoRepository.save(proyectoExistente);
        } else {
            return null; // O manejar de otra forma si no se encuentra el proyecto
        }
    }

    // Método para eliminar un proyecto por ID
    public boolean eliminarProyecto(int idProyecto) {
        Optional<Proyectos> optionalProyecto = proyectoRepository.findById(idProyecto);
        if (optionalProyecto.isPresent()) {
            proyectoRepository.delete(optionalProyecto.get());
            return true;
        } else {
            return false; // O manejar de otra forma si no se encuentra el proyecto
        }
    }

    // Método para obtener todos los proyectos de un empleado
    public List<Proyectos> obtenerProyectosPorEmpleado(int idEmpleado) {
        return empleadoProyectoRepository.findProyectosByEmpleadoId(idEmpleado);
    }


}
