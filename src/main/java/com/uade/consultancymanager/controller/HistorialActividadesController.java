package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.HistorialActividades;
import com.uade.consultancymanager.repository.HistorialActividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/actividades-log")
public class HistorialActividadesController {

    @Autowired
    private HistorialActividadesRepository activityLogRepository;

    @PostMapping
    public ResponseEntity<HistorialActividades> registrarActividad(@RequestBody HistorialActividades activityLog) {
        activityLog.setFechaHora(LocalDateTime.now());
        HistorialActividades actividadRegistrada = activityLogRepository.save(activityLog);
        return new ResponseEntity<>(actividadRegistrada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<HistorialActividades>> obtenerHistorialActividades(
            @RequestParam(required = false) Integer projectId,
            @RequestParam(required = false) Integer taskId) {
        Iterable<HistorialActividades> actividades;
        if (projectId != null && taskId != null) {
            actividades = activityLogRepository.findByProyectoIdAndTareaId(projectId, taskId);
        } else if (projectId != null) {
            actividades = activityLogRepository.findByProyectoId(projectId);
        } else if (taskId != null) {
            actividades = activityLogRepository.findByTareaId(taskId);
        } else {
            actividades = activityLogRepository.findAll();
        }
        return new ResponseEntity<>(actividades, HttpStatus.OK);
    }
}