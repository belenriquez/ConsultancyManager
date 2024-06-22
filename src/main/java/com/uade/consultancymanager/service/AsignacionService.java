package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Asignaciones;
import com.uade.consultancymanager.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AsignacionService {

    private final AsignacionRepository assignmentRepository;

    @Autowired
    public AsignacionService(AsignacionRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    // Método para asignar un empleado a una tarea
    public Asignaciones asignarEmpleadoATarea(Asignaciones asignaciones) {
        return assignmentRepository.save(asignaciones);
    }

    // Método para obtener una asignación por ID
    public Asignaciones obtenerAsignacionPorId(int idAsignacion) {
        return assignmentRepository.findById(idAsignacion).orElse(null);
    }

    // Método para obtener todas las asignaciones
    public List<Asignaciones> obtenerTodasAsignaciones() {
        return assignmentRepository.findAll();
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