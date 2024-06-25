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
public interface EmpleadoProyectosRepository extends JpaRepository<EmpleadoProyecto, Long> {

    @Query("SELECT ep.empleadoId FROM EmpleadoProyecto ep WHERE ep.proyectoId = :proyectoId")
    List<Empleados> findEmpleadosByProyectoId(@Param("proyectoId") int proyectoId);

    @Query("SELECT ep.proyectoId FROM EmpleadoProyecto ep WHERE ep.empleadoId = :empleadoId")
    List<Proyectos> findProyectosByEmpleadoId(@Param("empleadoId") int empleadoId);

    boolean existsByEmpleadoIdAndProyectoId(int empleadoId, int proyectoId);

    List<EmpleadoProyecto> findByProyectoId(int proyectoId);

    void deleteByEmpleadoIdAndProyectoId(int empleadoId, int proyectoId);
}