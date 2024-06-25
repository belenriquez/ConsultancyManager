package com.uade.consultancymanager.service;

import com.uade.consultancymanager.entity.EmpleadoProyecto;
import com.uade.consultancymanager.repository.EmpleadoProyectosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpleadoProyectoService {
    @Autowired
    private EmpleadoProyectosRepository empleadoProyectoRepository;

    // Método para asignar un empleado a un proyecto
    public EmpleadoProyecto asignarEmpleadoAProyecto(int empleadoId, int proyectoId) {
        EmpleadoProyecto empleadoProyecto = new EmpleadoProyecto();

        // Aquí puedes validar si la relación ya existe antes de crearla
        empleadoProyecto.setEmpleadoId(empleadoId);
        empleadoProyecto.setProyectoId(proyectoId);

        return empleadoProyectoRepository.save(empleadoProyecto);
    }

    // Método para obtener la lista de empleados asignados a un proyecto
    public List<EmpleadoProyecto> obtenerEmpleadosPorProyecto(int proyectoId) {
        return empleadoProyectoRepository.findByProyectoId(proyectoId);
    }

    // Método para eliminar la asignación de un empleado a un proyecto
    public void eliminarAsignacion(int empleadoId, int proyectoId) {
        empleadoProyectoRepository.deleteByEmpleadoIdAndProyectoId(empleadoId, proyectoId);
    }
}
