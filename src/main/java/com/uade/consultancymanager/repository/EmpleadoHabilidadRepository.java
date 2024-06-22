package com.uade.consultancymanager.repository;

import com.uade.consultancymanager.entity.EmpleadoHabilidad;
import com.uade.consultancymanager.entity.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoHabilidadRepository extends JpaRepository<EmpleadoHabilidad, Long> {
    void deleteByEmpleado(Empleados empleado);
}
