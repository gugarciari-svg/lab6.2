package com.empresa.vehiculos.model;

/**
 * Clase base abstracta con los atributos comunes a todo tipo de vehículo.
 * Las subclases deben implementar getTipo() y getDetalles().
 */
public abstract class Vehiculo {

    private String marca;
    private String color;
    private String placa;
    private String modelo;

    protected Vehiculo(String marca, String color, String placa, String modelo) {
        this.marca  = marca;
        this.color  = color;
        this.placa  = placa;
        this.modelo = modelo;
    }

    // ── Getters y Setters ────────────────────────────────────────────────

    public String getMarca()  { return marca;  }
    public void setMarca(String marca)   { this.marca  = marca;  }

    public String getColor()  { return color;  }
    public void setColor(String color)   { this.color  = color;  }

    public String getPlaca()  { return placa;  }
    public void setPlaca(String placa)   { this.placa  = placa;  }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    // ── Métodos abstractos ───────────────────────────────────────────────

    /** Retorna la etiqueta del tipo de vehículo (p. ej. "Camión"). */
    public abstract String getTipo();

    /** Retorna un resumen de los atributos específicos del vehículo. */
    public abstract String getDetalles();

    // ── Object ───────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return getTipo() + " [" + placa + "] " + marca + " " + modelo;
    }
}
