package com.uade.consultancymanager.entity;

import jakarta.persistence.*;

@Entity
public class Asignaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asignacion_id")
    private Long asignacionId;

    @Column(name = "proyecto_id", nullable = false)
    private Long proyectoId;

    @Column(name = "empleado_id", nullable = false)
    private Long empleadoId;

    @Column(name = "tarea_id")
    private Long tareaId;

    @Column(name = "rol", length = 50)
    private String rol;

    // Getters y setters

    public Long getAsignacionId() {
        return asignacionId;
    }

    public void setAsignacionId(Long asignacionId) {
        this.asignacionId = asignacionId;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getTareaId() {
        return tareaId;
    }

    public void setTareaId(Long tareaId) {
        this.tareaId = tareaId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}