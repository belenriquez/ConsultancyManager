package com.uade.consultancymanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados_habilidades")
public class EmpleadoHabilidad {

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleados empleado;

    @ManyToOne
    @JoinColumn(name = "habilidad_id")
    private Habilidad habilidad;
    @Id
    private Long id;

    public EmpleadoHabilidad(Empleados empleado, Habilidad habilidad) {
        this.empleado = empleado;
        this.habilidad = habilidad;
    }

    public EmpleadoHabilidad() {

    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}