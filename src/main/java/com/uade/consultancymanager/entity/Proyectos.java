package com.uade.consultancymanager.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Proyectos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proyecto_id")
    private int proyectoId;

    @Column(name = "nombre", nullable = false, length = 255, unique = true)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;


    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}