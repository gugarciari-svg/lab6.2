package com.empresa.vehiculos.model;

/**
 * Bus de servicio intermunicipal.
 * Atributos específicos: empresa, chofer, ciudad de origen y ciudad de destino.
 */
public class BusIntermunicipal extends Vehiculo {

    private String empresa;
    private String chofer;
    private String ciudadOrigen;
    private String ciudadDestino;

    public BusIntermunicipal(String marca, String color, String placa, String modelo,
                             String empresa, String chofer,
                             String ciudadOrigen, String ciudadDestino) {
        super(marca, color, placa, modelo);
        this.empresa       = empresa;
        this.chofer        = chofer;
        this.ciudadOrigen  = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
    }

    // ── Getters y Setters ────────────────────────────────────────────────

    public String getEmpresa()                    { return empresa;       }
    public void   setEmpresa(String e)            { this.empresa       = e; }

    public String getChofer()                     { return chofer;        }
    public void   setChofer(String c)             { this.chofer        = c; }

    public String getCiudadOrigen()               { return ciudadOrigen;  }
    public void   setCiudadOrigen(String origen)  { this.ciudadOrigen  = origen; }

    public String getCiudadDestino()              { return ciudadDestino; }
    public void   setCiudadDestino(String dest)   { this.ciudadDestino = dest; }

    // ── Vehiculo ─────────────────────────────────────────────────────────

    @Override
    public String getTipo() { return "Bus Intermunicipal"; }

    @Override
    public String getDetalles() {
        return String.format("Empresa: %s | Chofer: %s | %s -> %s",
                empresa, chofer, ciudadOrigen, ciudadDestino);
    }
}
