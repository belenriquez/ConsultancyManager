package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.Empleados;
import com.uade.consultancymanager.entity.Habilidad;
import com.uade.consultancymanager.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosController {

    private final EmpleadoService employeeService;

    @Autowired
    public EmpleadosController(EmpleadoService employeeService) {
        this.employeeService = employeeService;
    }

    // Endpoint para crear un empleado
    @PostMapping
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleados empleado) {
        try {
            Empleados empleadoCreado = employeeService.crearEmpleado(empleado);
            return new ResponseEntity<>(empleadoCreado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para obtener un empleado por ID
    @GetMapping("/{idEmpleado}")
    public ResponseEntity<Empleados> obtenerEmpleadoPorId(@PathVariable int idEmpleado) {
        Empleados empleado = employeeService.obtenerEmpleadoPorId(idEmpleado);
        if (empleado != null) {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleados>> obtenerTodosEmpleados() {
        List<Empleados> empleados = employeeService.obtenerTodosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    // Endpoint para actualizar un empleado por ID
    @PutMapping("/{idEmpleado}")
    public ResponseEntity<Empleados> actualizarEmpleado(@PathVariable int idEmpleado, @RequestBody Empleados empleado) {
        Empleados empleadoActualizado = employeeService.actualizarEmpleado(idEmpleado, empleado);
        if (empleadoActualizado != null) {
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un empleado por ID
    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable int idEmpleado) {
        boolean eliminado = employeeService.eliminarEmpleado(idEmpleado);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para asignar habilidades a un empleado
    @PostMapping("/{idEmpleado}/habilidades")
    public ResponseEntity<Empleados> asignarHabilidades(@PathVariable int idEmpleado, @RequestParam List<Integer> idsHabilidades) {
        Empleados empleado = employeeService.asignarHabilidades(idEmpleado, idsHabilidades);
        if (empleado != null) {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener todos los empleados asignados a un proyecto
    @GetMapping("/proyectos/{idProyecto}")
    public ResponseEntity<List<Empleados>> obtenerEmpleadosPorProyecto(@PathVariable int idProyecto) {
        List<Empleados> empleados = employeeService.obtenerEmpleadosPorProyecto(idProyecto);
        if (!empleados.isEmpty()) {
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}