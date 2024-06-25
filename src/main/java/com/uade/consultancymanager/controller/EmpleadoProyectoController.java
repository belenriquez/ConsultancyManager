package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.EmpleadoProyecto;
import com.uade.consultancymanager.service.EmpleadoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado-proyectos")
public class EmpleadoProyectoController {
    @Autowired
    private EmpleadoProyectoService empleadoProyectoService;

    // Endpoint para asignar un empleado a un proyecto
    @PostMapping("/")
    public ResponseEntity<EmpleadoProyecto> asignarEmpleadoAProyecto(@RequestParam("empleadoId") int empleadoId, @RequestParam("proyectoId") int proyectoId) {
        EmpleadoProyecto empleadoProyecto = empleadoProyectoService.asignarEmpleadoAProyecto(empleadoId, proyectoId);
        return new ResponseEntity<>(empleadoProyecto, HttpStatus.CREATED);
    }

    // Endpoint para obtener la lista de empleados asignados a un proyecto
    @GetMapping("/{proyectoId}/empleados")
    public ResponseEntity<List<EmpleadoProyecto>> obtenerEmpleadosPorProyecto(@PathVariable int proyectoId) {
        List<EmpleadoProyecto> empleadosProyecto = empleadoProyectoService.obtenerEmpleadosPorProyecto(proyectoId);
        return new ResponseEntity<>(empleadosProyecto, HttpStatus.OK);
    }

    // Endpoint para eliminar la asignación de un empleado a un proyecto
    @DeleteMapping("/")
    public ResponseEntity<Void> eliminarAsignacion(@RequestParam("empleadoId") int empleadoId, @RequestParam("proyectoId") int proyectoId) {
        empleadoProyectoService.eliminarAsignacion(empleadoId, proyectoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
