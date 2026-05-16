package com.hospital.gestion.model;

public abstract class Persona {
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected int edad;

    public Persona(String nombre, String direccion, String telefono, int edad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.edad = edad;
    }
}