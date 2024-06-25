package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.Proyectos;
import com.uade.consultancymanager.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectosController {

    @Autowired
    private ProyectoService proyectoService;

    // Endpoint para crear un proyecto
    @PostMapping
    public ResponseEntity<?> crearProyecto(@RequestBody Proyectos proyecto) {
        try {
            Proyectos proyectoCreado = proyectoService.crearProyecto(proyecto);
            return new ResponseEntity<>(proyectoCreado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para obtener un proyecto por ID
    @GetMapping("/{idProyecto}")
    public ResponseEntity<Proyectos> obtenerProyectoPorId(@PathVariable int idProyecto) {
        Proyectos proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
        if (proyecto != null) {
            return new ResponseEntity<>(proyecto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener todos los proyectos
    @GetMapping
    public ResponseEntity<List<Proyectos>> obtenerTodosProyectos() {
        List<Proyectos> proyectos = proyectoService.obtenerTodosProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    // Endpoint para actualizar un proyecto por ID
    @PutMapping("/{idProyecto}")
    public ResponseEntity<Proyectos> actualizarProyecto(@PathVariable int idProyecto, @RequestBody Proyectos proyecto) {
        Proyectos proyectoActualizado = proyectoService.actualizarProyecto(idProyecto, proyecto);
        if (proyectoActualizado != null) {
            return new ResponseEntity<>(proyectoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un proyecto por ID
    @DeleteMapping("/{idProyecto}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable int idProyecto) {
        boolean eliminado = proyectoService.eliminarProyecto(idProyecto);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<Proyectos>> obtenerProyectosPorEmpleado(@PathVariable int idEmpleado) {
        List<Proyectos> proyectos = proyectoService.obtenerProyectosPorEmpleado(idEmpleado);
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

}