package com.uade.consultancymanager.repository;

import com.uade.consultancymanager.entity.HistorialActividades;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialActividadesRepository extends MongoRepository<HistorialActividades, String> {

    Iterable<HistorialActividades> findByProyectoId(int proyectoId);

    Iterable<HistorialActividades> findByTareaId(int tareaId);

    Iterable<HistorialActividades> findByProyectoIdAndTareaId(int proyectoId, int tareaId);
}