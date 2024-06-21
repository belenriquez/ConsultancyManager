package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.ComentariosTareas;
import com.uade.consultancymanager.repository.ComentariosTareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/comentarios")
public class ComentariosTareasController {

    @Autowired
    private ComentariosTareasRepository taskCommentRepository;

    @PostMapping
    public ResponseEntity<ComentariosTareas> agregarComentario(@RequestBody ComentariosTareas taskComment) {
        taskComment.setFechaHora(LocalDateTime.now());
        ComentariosTareas comentarioAgregado = taskCommentRepository.save(taskComment);
        return new ResponseEntity<>(comentarioAgregado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<ComentariosTareas>> obtenerComentariosPorTarea(
            @RequestParam(required = true) int taskId) {
        Iterable<ComentariosTareas> comentarios = taskCommentRepository.findByTareaId(taskId);
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }
}