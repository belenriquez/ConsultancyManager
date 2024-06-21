package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.SesionUsuario;
import com.uade.consultancymanager.repository.SesionUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class SesionUsuarioController {

    @Autowired
    private SesionUsuarioRepository sessionRepository;

    @PostMapping
    public SesionUsuario createSession(@RequestBody SesionUsuario session) {
        return sessionRepository.save(session);
    }

    @GetMapping("/{sessionId}")
    public Optional<SesionUsuario> getSession(@PathVariable String sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public String deleteSession(@PathVariable String sessionId) {
        sessionRepository.deleteById(sessionId);
        return "Sesión eliminada correctamente";
    }
}