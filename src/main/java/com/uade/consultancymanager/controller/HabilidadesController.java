package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.Habilidad;
import com.uade.consultancymanager.service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadesController {

    @Autowired
    private HabilidadService habilidadService;

    // Endpoint para crear una nueva habilidad
    @PostMapping
    public ResponseEntity<Habilidad> crearHabilidad(@RequestBody Habilidad habilidad) {
        Habilidad habilidadCreada = habilidadService.crearHabilidad(habilidad);
        return new ResponseEntity<>(habilidadCreada, HttpStatus.CREATED);
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
