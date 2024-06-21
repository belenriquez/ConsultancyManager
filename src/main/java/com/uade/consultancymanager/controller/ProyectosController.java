package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.Proyectos;
import com.uade.consultancymanager.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectosController {

    @Autowired
    private ProyectoService projectService;

    // Endpoint para crear un proyecto
    @PostMapping
    public ResponseEntity<Proyectos> crearProyecto(@RequestBody Proyectos proyecto) {
        Proyectos proyectoCreado = projectService.crearProyecto(proyecto);
        return new ResponseEntity<>(proyectoCreado, HttpStatus.CREATED);
    }

    // Endpoint para obtener un proyecto por ID
    @GetMapping("/{idProyecto}")
    public ResponseEntity<Proyectos> obtenerProyectoPorId(@PathVariable int idProyecto) {
        Proyectos proyecto = projectService.obtenerProyectoPorId(idProyecto);
        if (proyecto != null) {
            return new ResponseEntity<>(proyecto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar un proyecto por ID
    @PutMapping("/{idProyecto}")
    public ResponseEntity<Proyectos> actualizarProyecto(@PathVariable int idProyecto, @RequestBody Proyectos proyecto) {
        Proyectos proyectoActualizado = projectService.actualizarProyecto(idProyecto, proyecto);
        if (proyectoActualizado != null) {
            return new ResponseEntity<>(proyectoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un proyecto por ID
    @DeleteMapping("/{idProyecto}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable int idProyecto) {
        boolean eliminado = projectService.eliminarProyecto(idProyecto);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}