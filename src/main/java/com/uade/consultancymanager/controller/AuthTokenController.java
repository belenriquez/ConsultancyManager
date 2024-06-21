package com.uade.consultancymanager.controller;

import com.uade.consultancymanager.entity.AuthToken;
import com.uade.consultancymanager.repository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tokens")
public class AuthTokenController {

    @Autowired
    private AuthTokenRepository tokenRepository;

    @PostMapping
    public AuthToken createToken(@RequestBody AuthToken token) {
        return tokenRepository.save(token);
    }

    @GetMapping("/{tokenId}")
    public Optional<AuthToken> getToken(@PathVariable String tokenId) {
        return tokenRepository.findById(tokenId);
    }

    @DeleteMapping("/{tokenId}")
    public String deleteToken(@PathVariable String tokenId) {
        tokenRepository.deleteById(tokenId);
        return "Token eliminado correctamente";
    }
}