package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.Empleados;
import com.uade.consultancymanager.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository employeeRepository;

    // Método para crear un empleado
    public Empleados crearEmpleado(Empleados empleado) {
        return employeeRepository.save(empleado);
    }

    // Método para obtener un empleado por ID
    public Empleados obtenerEmpleadoPorId(int idEmpleado) {
        return employeeRepository.findById(idEmpleado).orElse(null);
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
}