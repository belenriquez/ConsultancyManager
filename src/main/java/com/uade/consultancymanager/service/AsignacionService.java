package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Asignaciones;
import com.uade.consultancymanager.repository.AsignacionRepository;
import com.uade.consultancymanager.repository.EmpleadoProyectosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AsignacionService {

    private final AsignacionRepository assignmentRepository;
    private final EmpleadoProyectosRepository empleadoProyectoRepository;

    @Autowired
    public AsignacionService(AsignacionRepository assignmentRepository, EmpleadoProyectosRepository empleadoProyectoRepository) {
        this.assignmentRepository = assignmentRepository;
        this.empleadoProyectoRepository = empleadoProyectoRepository;
    }

    // Método para asignar un empleado a una tarea
    public Asignaciones asignarEmpleadoATarea(Asignaciones asignacion) {
        int empleadoId = asignacion.getEmpleadoId();
        int proyectoId = asignacion.getProyectoId();

        // Verificar si el empleado está asignado al proyecto
        boolean empleadoAsignado = empleadoProyectoRepository.existsByEmpleadoAndProyecto(empleadoId, proyectoId);
        if (!empleadoAsignado) {
            throw new IllegalArgumentException("El empleado no pertenece al proyecto especificado.");
        }

        return assignmentRepository.save(asignacion);
    }

    // Método para obtener una asignación por ID
    public Asignaciones obtenerAsignacionPorId(int idAsignacion) {
        return assignmentRepository.findById(idAsignacion).orElse(null);
    }

    // Método para actualizar una asignación por ID
    public Asignaciones actualizarAsignacion(int idAsignacion, Asignaciones asignacionesActualizada) {
        Asignaciones asignacionesExistente = assignmentRepository.findById(idAsignacion).orElse(null);
        if (asignacionesExistente != null) {
            asignacionesExistente.setProyectoId(asignacionesActualizada.getProyectoId());
            asignacionesExistente.setEmpleadoId(asignacionesActualizada.getEmpleadoId());
            asignacionesExistente.setTareaId(asignacionesActualizada.getTareaId());
            asignacionesExistente.setRol(asignacionesActualizada.getRol());
            return assignmentRepository.save(asignacionesExistente);
        } else {
            return null;
        }
    }

    // Método para eliminar una asignación por ID
    public boolean eliminarAsignacion(int idAsignacion) {
        Asignaciones asignacionesExistente = assignmentRepository.findById(idAsignacion).orElse(null);
        if (asignacionesExistente != null) {
            assignmentRepository.delete(asignacionesExistente);
            return true;
        } else {
            return false;
        }
    }
}