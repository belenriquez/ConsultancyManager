package com.uade.consultancymanager.repository;
import com.uade.consultancymanager.entity.Asignaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignaciones, Integer>{
    List<Asignaciones> findByEmpleadoId(int empleadoId);
}
