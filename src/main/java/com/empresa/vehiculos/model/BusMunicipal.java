package com.empresa.vehiculos.model;

/**
 * Bus de servicio municipal.
 * Atributos específicos: empresa, chofer y ruta.
 */
public class BusMunicipal extends Vehiculo {

    private String empresa;
    private String chofer;
    private String ruta;

    public BusMunicipal(String marca, String color, String placa, String modelo,
                        String empresa, String chofer, String ruta) {
        super(marca, color, placa, modelo);
        this.empresa = empresa;
        this.chofer  = chofer;
        this.ruta    = ruta;
    }

    // ── Getters y Setters ────────────────────────────────────────────────

    public String getEmpresa()            { return empresa; }
    public void   setEmpresa(String e)    { this.empresa = e; }

    public String getChofer()             { return chofer;  }
    public void   setChofer(String c)     { this.chofer  = c; }

    public String getRuta()               { return ruta;    }
    public void   setRuta(String r)       { this.ruta    = r; }

    // ── Vehiculo ─────────────────────────────────────────────────────────

    @Override
    public String getTipo() { return "Bus Municipal"; }

    @Override
    public String getDetalles() {
        return String.format("Empresa: %s | Chofer: %s | Ruta: %s", empresa, chofer, ruta);
    }
}
