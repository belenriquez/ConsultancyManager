package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Tareas;
import com.uade.consultancymanager.repository.ProyectoRepository;
import com.uade.consultancymanager.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TareaService {

    private final TareaRepository taskRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    public TareaService(TareaRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Método para crear una tarea
    public Tareas crearTarea(Tareas tarea) {
        if (tarea.getProyectoId() == null || !proyectoRepository.existsById(tarea.getProyectoId())) {
            throw new IllegalArgumentException("Proyecto no válido o no encontrado");
        }
        return taskRepository.save(tarea);
    }

    // Método para obtener una tarea por ID
    public Tareas obtenerTareaPorId(int idTarea) {
        return taskRepository.findById(idTarea).orElse(null);
    }

    // Método para obtener todas las tareas
    public List<Tareas> obtenerTodasTareas() {
        return taskRepository.findAll();
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