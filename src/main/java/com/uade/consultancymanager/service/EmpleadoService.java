package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.EmpleadoHabilidad;
import com.uade.consultancymanager.entity.Empleados;
import com.uade.consultancymanager.entity.Habilidad;
import com.uade.consultancymanager.repository.EmpleadoHabilidadRepository;
import com.uade.consultancymanager.repository.EmpleadoProyectosRepository;
import com.uade.consultancymanager.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {

    private final EmpleadoRepository employeeRepository;
    private final EmpleadoHabilidadRepository empleadoHabilidadRepository;
    private final EmpleadoProyectosRepository empleadoProyectoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository employeeRepository, EmpleadoHabilidadRepository empleadoHabilidadRepository, EmpleadoProyectosRepository empleadoProyectoRepository) {
        this.employeeRepository = employeeRepository;
        this.empleadoHabilidadRepository = empleadoHabilidadRepository;
        this.empleadoProyectoRepository = empleadoProyectoRepository;
    }

    // Método para crear un empleado
    public Empleados crearEmpleado(Empleados empleado) {
        if (employeeRepository.existsByEmail(empleado.getEmail())) {
            throw new IllegalArgumentException("Ya existe un empleado con el mismo email.");
        }
        return employeeRepository.save(empleado);
    }

    // Método para obtener un empleado por ID
    public Empleados obtenerEmpleadoPorId(int idEmpleado) {
        return employeeRepository.findById(idEmpleado).orElse(null);
    }

    // Método para obtener todos los empleados
    public List<Empleados> obtenerTodosEmpleados() {
        return employeeRepository.findAll();
    }

    // Método para actualizar un empleado por ID
    public Empleados actualizarEmpleado(int idEmpleado, Empleados empleadoActualizado) {
        Empleados empleadoExistente = employeeRepository.findById(idEmpleado).orElse(null);
        if (empleadoExistente != null) {
            empleadoExistente.setNombre(empleadoActualizado.getNombre());
            empleadoExistente.setApellido(empleadoActualizado.getApellido());
            empleadoExistente.setEmail(empleadoActualizado.getEmail());
            empleadoExistente.setDisponibilidad(empleadoActualizado.getDisponibilidad());
            return employeeRepository.save(empleadoExistente);
        } else {
            return null;
        }
    }

    // Método para eliminar un empleado por ID
    public boolean eliminarEmpleado(int idEmpleado) {
        Empleados empleadoExistente = employeeRepository.findById(idEmpleado).orElse(null);
        if (empleadoExistente != null) {
            employeeRepository.delete(empleadoExistente);
            return true;
        } else {
            return false;
        }
    }

    // Método para actualizar un empleado y asignarle habilidades
    public Empleados asignarHabilidades(int idEmpleado, List<Integer> idsHabilidades) {
        Optional<Empleados> empleadoOptional = employeeRepository.findById(idEmpleado);
        if (empleadoOptional.isPresent()) {
            Empleados empleado = empleadoOptional.get();

            // Eliminar habilidades actuales
            empleadoHabilidadRepository.deleteByEmpleado(empleado);

            // Asignar nuevas habilidades
            for (int idHabilidad : idsHabilidades) {
                Habilidad habilidad = new Habilidad(); // Lógica para obtener habilidad por ID
                habilidad.setHabilidadId(idHabilidad);
                EmpleadoHabilidad empHabilidad = new EmpleadoHabilidad(empleado, habilidad);
                empleadoHabilidadRepository.save(empHabilidad);
            }

            return employeeRepository.save(empleado);
        }
        return null;
    }

    // Método para obtener todos los empleados asignados a un proyecto
    public List<Empleados> obtenerEmpleadosPorProyecto(int idProyecto) {
        return empleadoProyectoRepository.findEmpleadoProyectosByProyectoId(idProyecto);
    }
}