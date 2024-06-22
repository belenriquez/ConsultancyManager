package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.Habilidad;
import com.uade.consultancymanager.service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadesController {

    private final HabilidadService habilidadService;

    @Autowired
    public HabilidadesController(HabilidadService habilidadService) {
        this.habilidadService = habilidadService;
    }

    // Endpoint para crear una nueva habilidad
    @PostMapping
    public ResponseEntity<?> crearHabilidad(@RequestBody Habilidad habilidad) {
        try {
            Habilidad habilidadCreada = habilidadService.crearHabilidad(habilidad);
            return new ResponseEntity<>(habilidadCreada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para obtener una habilidad por ID
    @GetMapping("/{idHabilidad}")
    public ResponseEntity<Habilidad> obtenerHabilidadPorId(@PathVariable int idHabilidad) {
        Habilidad habilidad = habilidadService.obtenerHabilidadPorId(idHabilidad);
        if (habilidad != null) {
            return new ResponseEntity<>(habilidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener todas las habilidades
    @GetMapping
    public ResponseEntity<List<Habilidad>> obtenerTodasHabilidades() {
        List<Habilidad> habilidades = habilidadService.obtenerTodasHabilidades();
        return new ResponseEntity<>(habilidades, HttpStatus.OK);
    }

    // Endpoint para actualizar una habilidad por ID
    @PutMapping("/{idHabilidad}")
    public ResponseEntity<Habilidad> actualizarHabilidad(@PathVariable int idHabilidad, @RequestBody Habilidad habilidad) {
        Habilidad habilidadActualizada = habilidadService.actualizarHabilidad(idHabilidad, habilidad);
        if (habilidadActualizada != null) {
            return new ResponseEntity<>(habilidadActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una habilidad por ID
    @DeleteMapping("/{idHabilidad}")
    public ResponseEntity<Void> eliminarHabilidad(@PathVariable int idHabilidad) {
        boolean eliminado = habilidadService.eliminarHabilidad(idHabilidad);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
