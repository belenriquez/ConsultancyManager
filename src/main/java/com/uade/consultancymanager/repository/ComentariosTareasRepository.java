package com.uade.consultancymanager.repository;

import com.uade.consultancymanager.entity.ComentariosTareas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosTareasRepository extends MongoRepository<ComentariosTareas, String> {

    Iterable<ComentariosTareas> findByTareaId(int tareaId);
}