package com.empresa.vehiculos.model;

/**
 * Vehículo de tipo Camión.
 * Atributos específicos: capacidad (toneladas) y tipo de carga.
 */
public class Camion extends Vehiculo {

    private double capacidad;   // en toneladas
    private String tipoCarga;

    public Camion(String marca, String color, String placa, String modelo,
                  double capacidad, String tipoCarga) {
        super(marca, color, placa, modelo);
        this.capacidad = capacidad;
        this.tipoCarga = tipoCarga;
    }

    // ── Getters y Setters ────────────────────────────────────────────────

    public double getCapacidad()            { return capacidad; }
    public void   setCapacidad(double cap)  { this.capacidad = cap; }

    public String getTipoCarga()            { return tipoCarga; }
    public void   setTipoCarga(String tipo) { this.tipoCarga = tipo; }

    // ── Vehiculo ─────────────────────────────────────────────────────────

    @Override
    public String getTipo() { return "Camión"; }

    @Override
    public String getDetalles() {
        return String.format("Capacidad: %.1f ton | Carga: %s", capacidad, tipoCarga);
    }
}
