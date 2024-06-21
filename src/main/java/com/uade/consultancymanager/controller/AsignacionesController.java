package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.Asignaciones;
import com.uade.consultancymanager.service.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionesController {

    @Autowired
    private AsignacionService assignmentService;

    // Endpoint para asignar un empleado a una tarea
    @PostMapping
    public ResponseEntity<Asignaciones> asignarEmpleadoATarea(@RequestBody Asignaciones asignaciones) {
        Asignaciones asignacionesCreada = assignmentService.asignarEmpleadoATarea(asignaciones);
        return new ResponseEntity<>(asignacionesCreada, HttpStatus.CREATED);
    }

    // Endpoint para obtener una asignación por ID
    @GetMapping("/{idAsignacion}")
    public ResponseEntity<Asignaciones> obtenerAsignacionPorId(@PathVariable int idAsignacion) {
        Asignaciones asignaciones = assignmentService.obtenerAsignacionPorId(idAsignacion);
        if (asignaciones != null) {
            return new ResponseEntity<>(asignaciones, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una asignación por ID
    @DeleteMapping("/{idAsignacion}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable int idAsignacion) {
        boolean eliminado = assignmentService.eliminarAsignacion(idAsignacion);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}