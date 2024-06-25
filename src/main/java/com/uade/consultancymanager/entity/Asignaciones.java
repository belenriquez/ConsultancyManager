package com.uade.consultancymanager.entity;

import jakarta.persistence.*;

@Entity
public class Asignaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asignacion_id")
    private int asignacionId;

    @Column(name = "proyecto_id", nullable = false)
    private int proyectoId;

    @Column(name = "empleado_id", nullable = false)
    private int empleadoId;

    @Column(name = "tarea_id", nullable = false)
    private int tareaId;

    @Column(name = "rol", length = 50)
    private String rol;



    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getAsignacionId() {
        return asignacionId;
    }

    public void setAsignacionId(int asignacionId) {
        this.asignacionId = asignacionId;
    }

    public int getTareaId() {
        return tareaId;
    }

    public void setTareaId(int tareaId) {
        this.tareaId = tareaId;
    }
}