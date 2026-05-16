package com.hospital.gestion.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Enfermera extends Empleado {
    private String categoria; // auxiliar, instrumentadora, profesional

    public Enfermera(String nombre, String direccion, String telefono, int edad,
                     LocalDate fechaIngreso, LocalDate fechaTerminacion,
                     LocalTime horaEntrada, LocalTime horaSalida, double salario,
                     String categoria) {
        super(nombre, direccion, telefono, edad, fechaIngreso, fechaTerminacion, horaEntrada, horaSalida, salario);
        this.categoria = categoria;
    }
}