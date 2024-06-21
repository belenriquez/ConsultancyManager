package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Tareas;
import com.uade.consultancymanager.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TareaService {

    @Autowired
    private TareaRepository taskRepository;

    // Método para crear una tarea
    public Tareas crearTarea(Tareas tarea) {
        return taskRepository.save(tarea);
    }

    // Método para obtener una tarea por ID
    public Tareas obtenerTareaPorId(int idTarea) {
        return taskRepository.findById(idTarea).orElse(null);
    }

    // Método para actualizar una tarea por ID
    public Tareas actualizarTarea(int idTarea, Tareas tareaActualizada) {
        Tareas tareaExistente = taskRepository.findById(idTarea).orElse(null);
        if (tareaExistente != null) {
            tareaExistente.setNombre(tareaActualizada.getNombre());
            tareaExistente.setDescripcion(tareaActualizada.getDescripcion());
            tareaExistente.setFechaInicio(tareaActualizada.getFechaInicio());
            tareaExistente.setFechaFin(tareaActualizada.getFechaFin());
            tareaExistente.setEstado(tareaActualizada.getEstado());
            tareaExistente.setProgreso(tareaActualizada.getProgreso());
            tareaExistente.setAsignadoA(tareaActualizada.getAsignadoA());
            tareaExistente.setPrioridad(tareaActualizada.getPrioridad());
            tareaExistente.setHorasEstimadas(tareaActualizada.getHorasEstimadas());
            return taskRepository.save(tareaExistente);
        } else {
            return null;
        }
    }

    // Método para eliminar una tarea por ID
    public boolean eliminarTarea(int idTarea) {
        Tareas tareaExistente = taskRepository.findById(idTarea).orElse(null);
        if (tareaExistente != null) {
            taskRepository.delete(tareaExistente);
            return true;
        } else {
            return false;
        }
    }
}