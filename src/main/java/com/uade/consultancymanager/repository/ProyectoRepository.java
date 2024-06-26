package com.uade.consultancymanager.repository;

import com.uade.consultancymanager.entity.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyectos, Integer> {
    boolean existsByNombre(String nombre);
}