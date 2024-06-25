package com.uade.consultancymanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado_proyectos")
public class EmpleadoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleados empleado;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyectos proyecto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }
}
