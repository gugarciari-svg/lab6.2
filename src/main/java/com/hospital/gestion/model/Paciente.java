package com.hospital.gestion.model;

import java.time.LocalDate;

public class Paciente extends Persona {
    private LocalDate fechaIngreso;
    private LocalDate fechaAlta;
    private String motivoConsulta;
    private String medicamentos;

    public Paciente(String nombre, String direccion, String telefono, int edad,
                    LocalDate fechaIngreso, LocalDate fechaAlta,
                    String motivoConsulta, String medicamentos) {
        super(nombre, direccion, telefono, edad);
        this.fechaIngreso = fechaIngreso;
        this.fechaAlta = fechaAlta;
        this.motivoConsulta = motivoConsulta;
        this.medicamentos = medicamentos;
    }
}