package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.ProgresoTarea;
import com.uade.consultancymanager.entity.Tareas;
import com.uade.consultancymanager.repository.ProgresoTareaRepository;
import com.uade.consultancymanager.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareasController {

    private final TareaService taskService;
    private final ProgresoTareaRepository progressRepository;

    @Autowired
    public TareasController(TareaService taskService, ProgresoTareaRepository progressRepository) {
        this.taskService = taskService;
        this.progressRepository = progressRepository;
    }

    @PutMapping("/{taskId}/progress")
    public ResponseEntity<ProgresoTarea> updateTaskProgress(@PathVariable int taskId, @RequestBody ProgresoTarea progress) {
        ProgresoTarea progresoActualizado = progressRepository.save(progress);
        return new ResponseEntity<>(progresoActualizado, HttpStatus.OK);
    }

    @GetMapping("/{taskId}/progress")
    public ResponseEntity<ProgresoTarea> getTaskProgress(@PathVariable int taskId) {
        Optional<ProgresoTarea> progresoTarea = progressRepository.findById(taskId);
        return progresoTarea.map(progreso -> new ResponseEntity<>(progreso, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para crear una tarea
    @PostMapping
    public ResponseEntity<?> crearTarea(@RequestBody Tareas tarea) {
        try {
            Tareas tareaCreada = taskService.crearTarea(tarea);
            return new ResponseEntity<>(tareaCreada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

    // Endpoint para obtener todas las tareas
    @GetMapping
    public ResponseEntity<List<Tareas>> obtenerTodasTareas() {
        List<Tareas> tareas = taskService.obtenerTodasTareas();
        return new ResponseEntity<>(tareas, HttpStatus.OK);
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

    // Endpoint para obtener tareas por proyecto
    @GetMapping("/proyecto/{idProyecto}")
    public ResponseEntity<List<Tareas>> obtenerTareasPorProyecto(@PathVariable int idProyecto) {
        List<Tareas> tareas = taskService.obtenerTareasPorProyecto(idProyecto);
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }
}