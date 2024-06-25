package com.uade.consultancymanager.repository;

import com.uade.consultancymanager.entity.EmpleadoProyecto;
import com.uade.consultancymanager.entity.Empleados;
import com.uade.consultancymanager.entity.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoProyectosRepository extends JpaRepository<EmpleadoProyecto, Integer> {

    @Query("SELECT ep.empleado FROM EmpleadoProyecto ep WHERE ep.proyecto.proyectoId = :proyectoId")
    List<Empleados> findEmpleadoProyectosByProyectoId(@Param("proyectoId") int proyectoId);

    @Query("SELECT ep.proyecto FROM EmpleadoProyecto ep WHERE ep.empleado.empleadoId = :empleadoId")
    List<Proyectos> findProyectosByEmpleadoId(@Param("empleadoId") int empleadoId);

    boolean existsByEmpleadoAndProyecto(int empleadoId, int proyectoId);

    List<EmpleadoProyecto> findByProyecto(int proyectoId);

    void deleteByEmpleadoAndProyecto(int empleadoId, int proyectoId);
}