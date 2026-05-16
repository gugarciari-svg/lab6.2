package com.hospital.gestion.model;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Empleado extends Persona {
    protected LocalDate fechaIngreso;
    protected LocalDate fechaTerminacion;
    protected LocalTime horaEntrada;
    protected LocalTime horaSalida;
    protected double salario;

    public Empleado(String nombre, String direccion, String telefono, int edad,
                    LocalDate fechaIngreso, LocalDate fechaTerminacion,
                    LocalTime horaEntrada, LocalTime horaSalida, double salario) {
        super(nombre, direccion, telefono, edad);
        this.fechaIngreso = fechaIngreso;
        this.fechaTerminacion = fechaTerminacion;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.salario = salario;
    }
}