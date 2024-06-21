package com.uade.consultancymanager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@RedisHash("AuthToken")
public class AuthToken implements Serializable {
    @Id
    private String tokenId;
    private int userId;
    private List<String> permissions;
    private LocalDateTime tiempoExpiracion;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public LocalDateTime getTiempoExpiracion() {
        return tiempoExpiracion;
    }

    public void setTiempoExpiracion(LocalDateTime tiempoExpiracion) {
        this.tiempoExpiracion = tiempoExpiracion;
    }
}