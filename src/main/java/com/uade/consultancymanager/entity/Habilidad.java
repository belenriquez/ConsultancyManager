package com.uade.consultancymanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidades")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habilidad_id")
    private int habilidadId;

    @Column(name = "nombre_habilidad", nullable = false, length = 100)
    private String nombreHabilidad;


    public int getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(int habilidadId) {
        this.habilidadId = habilidadId;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }
}