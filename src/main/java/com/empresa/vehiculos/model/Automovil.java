package com.empresa.vehiculos.model;

/**
 * Vehículo de tipo Automóvil.
 * Atributos específicos: número de puertas y disposición (Particular / Servicio Público).
 */
public class Automovil extends Vehiculo {

    private int    numeroPuertas;
    private String disposicion;   // "Particular" | "Servicio Público"

    public Automovil(String marca, String color, String placa, String modelo,
                     int numeroPuertas, String disposicion) {
        super(marca, color, placa, modelo);
        this.numeroPuertas = numeroPuertas;
        this.disposicion   = disposicion;
    }

    // ── Getters y Setters ────────────────────────────────────────────────

    public int    getNumeroPuertas()           { return numeroPuertas; }
    public void   setNumeroPuertas(int n)      { this.numeroPuertas = n; }

    public String getDisposicion()             { return disposicion; }
    public void   setDisposicion(String disp)  { this.disposicion = disp; }

    // ── Vehiculo ─────────────────────────────────────────────────────────

    @Override
    public String getTipo() { return "Automóvil"; }

    @Override
    public String getDetalles() {
        return String.format("Puertas: %d | %s", numeroPuertas, disposicion);
    }
}
