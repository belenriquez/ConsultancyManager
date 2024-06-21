package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.Empleados;
import com.uade.consultancymanager.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosController {

    @Autowired
    private EmpleadoService employeeService;

    // Endpoint para crear un empleado
    @PostMapping
    public ResponseEntity<Empleados> crearEmpleado(@RequestBody Empleados empleado) {
        Empleados empleadoCreado = employeeService.crearEmpleado(empleado);
        return new ResponseEntity<>(empleadoCreado, HttpStatus.CREATED);
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
}