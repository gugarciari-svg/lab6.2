package com.hospital.gestion.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Medico extends Empleado {
    private String especialidad;
    private int nivel; // 1: < 2 años, 2: 1-4 años, 3: > 3 años

    public Medico(String nombre, String direccion, String telefono, int edad,
                  LocalDate fechaIngreso, LocalDate fechaTerminacion,
                  LocalTime horaEntrada, LocalTime horaSalida, double salario,
                  String especialidad, int nivel) {
        super(nombre, direccion, telefono, edad, fechaIngreso, fechaTerminacion, horaEntrada, horaSalida, salario);
        this.especialidad = especialidad;
        this.nivel = nivel;
    }
}