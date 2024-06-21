package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Asignaciones;
import com.uade.consultancymanager.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AsignacionService {

    @Autowired
    private AsignacionRepository assignmentRepository;

    // Método para asignar un empleado a una tarea
    public Asignaciones asignarEmpleadoATarea(Asignaciones asignaciones) {
        return assignmentRepository.save(asignaciones);
    }

    // Método para obtener una asignación por ID
    public Asignaciones obtenerAsignacionPorId(int idAsignacion) {
        return assignmentRepository.findById(idAsignacion).orElse(null);
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