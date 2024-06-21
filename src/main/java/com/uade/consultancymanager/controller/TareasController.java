package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.ProgresoTarea;
import com.uade.consultancymanager.entity.Tareas;
import com.uade.consultancymanager.repository.ProgresoTareaRepository;
import com.uade.consultancymanager.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareasController {

    @Autowired
    private TareaService taskService;

    @Autowired
    private ProgresoTareaRepository progressRepository;

    @PutMapping("/{taskId}/progress")
    public ProgresoTarea updateTaskProgress(@PathVariable String taskId, @RequestBody ProgresoTarea progress) {
        return progressRepository.save(progress);
    }

    @GetMapping("/{taskId}/progress")
    public Optional<ProgresoTarea> getTaskProgress(@PathVariable String taskId) {
        return progressRepository.findById(taskId);
    }

    // Endpoint para crear una tarea
    @PostMapping
    public ResponseEntity<Tareas> crearTarea(@RequestBody Tareas tarea) {
        Tareas tareaCreada = taskService.crearTarea(tarea);
        return new ResponseEntity<>(tareaCreada, HttpStatus.CREATED);
    }

    // Endpoint para obtener una tarea por ID
    @GetMapping("/{idTarea}")
    public ResponseEntity<Tareas> obtenerTareaPorId(@PathVariable int idTarea) {
        Tareas tarea = taskService.obtenerTareaPorId(idTarea);
        if (tarea != null) {
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar una tarea por ID
    @PutMapping("/{idTarea}")
    public ResponseEntity<Tareas> actualizarTarea(@PathVariable int idTarea, @RequestBody Tareas tarea) {
        Tareas tareaActualizada = taskService.actualizarTarea(idTarea, tarea);
        if (tareaActualizada != null) {
            return new ResponseEntity<>(tareaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una tarea por ID
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable int idTarea) {
        boolean eliminada = taskService.eliminarTarea(idTarea);
        if (eliminada) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}