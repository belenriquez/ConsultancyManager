package com.uade.consultancymanager.repository;

import com.uade.consultancymanager.entity.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tareas, Integer> {
    List<Tareas> findByProyectoId(int proyectoId);
}
