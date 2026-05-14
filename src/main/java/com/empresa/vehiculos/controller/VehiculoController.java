package com.empresa.vehiculos.controller;

import com.empresa.vehiculos.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador principal de la vista.
 * Gestiona el CRUD de vehículos y la navegación entre formularios específicos.
 */
public class VehiculoController implements Initializable {

    // ── Selector de tipo ─────────────────────────────────────────────────
    @FXML private ComboBox<String> tipoCombo;

    // ── Campos comunes ───────────────────────────────────────────────────
    @FXML private TextField marcaField;
    @FXML private TextField colorField;
    @FXML private TextField placaField;
    @FXML private TextField modeloField;

    // ── Paneles específicos (se muestran/ocultan según el tipo) ──────────
    @FXML private VBox camionPane;
    @FXML private VBox automovilPane;
    @FXML private VBox busMunicipalPane;
    @FXML private VBox busInterPane;

    // ── Campos: Camión ───────────────────────────────────────────────────
    @FXML private TextField capacidadField;
    @FXML private TextField tipoCargaField;

    // ── Campos: Automóvil ────────────────────────────────────────────────
    @FXML private Spinner<Integer> puertasSpinner;
    @FXML private ComboBox<String> disposicionCombo;

    // ── Campos: Bus Municipal ────────────────────────────────────────────
    @FXML private TextField empresaMunField;
    @FXML private TextField choferMunField;
    @FXML private TextField rutaField;

    // ── Campos: Bus Intermunicipal ───────────────────────────────────────
    @FXML private TextField empresaInterField;
    @FXML private TextField choferInterField;
    @FXML private TextField ciudadOrigenField;
    @FXML private TextField ciudadDestinoField;

    // ── Controles de estado ──────────────────────────────────────────────
    @FXML private Button agregarBtn;
    @FXML private Button actualizarBtn;
    @FXML private Label  statusLabel;
    @FXML private Label  countLabel;

    // ── Tabla ────────────────────────────────────────────────────────────
    @FXML private TableView<Vehiculo>              vehiculosTable;
    @FXML private TableColumn<Vehiculo, String>    tipoCol;
    @FXML private TableColumn<Vehiculo, String>    marcaCol;
    @FXML private TableColumn<Vehiculo, String>    colorCol;
    @FXML private TableColumn<Vehiculo, String>    placaCol;
    @FXML private TableColumn<Vehiculo, String>    modeloCol;
    @FXML private TableColumn<Vehiculo, String>    detallesCol;

    // ── Datos en memoria ─────────────────────────────────────────────────
    private final ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList();
    private Vehiculo vehiculoEnEdicion = null;

    // ── Constantes de tipo ───────────────────────────────────────────────
    private static final String CAMION             = "Camión";
    private static final String AUTOMOVIL          = "Automóvil";
    private static final String BUS_MUNICIPAL      = "Bus Municipal";
    private static final String BUS_INTERMUNICIPAL = "Bus Intermunicipal";

    // ════════════════════════════════════════════════════════════════════
    //  INICIALIZACIÓN
    // ════════════════════════════════════════════════════════════════════

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCombos();
        configurarSpinner();
        configurarTabla();
        ocultarPaneles();
    }

    private void configurarCombos() {
        tipoCombo.setItems(FXCollections.observableArrayList(
                CAMION, AUTOMOVIL, BUS_MUNICIPAL, BUS_INTERMUNICIPAL));

        disposicionCombo.setItems(FXCollections.observableArrayList(
                "Particular", "Servicio Público"));
    }

    private void configurarSpinner() {
        puertasSpinner.setValueFactory(new IntegerSpinnerValueFactory(2, 5, 4));
    }

    private void configurarTabla() {
        tipoCol.setCellValueFactory(     d -> new SimpleStringProperty(d.getValue().getTipo()));
        marcaCol.setCellValueFactory(    d -> new SimpleStringProperty(d.getValue().getMarca()));
        colorCol.setCellValueFactory(    d -> new SimpleStringProperty(d.getValue().getColor()));
        placaCol.setCellValueFactory(    d -> new SimpleStringProperty(d.getValue().getPlaca()));
        modeloCol.setCellValueFactory(   d -> new SimpleStringProperty(d.getValue().getModelo()));
        detallesCol.setCellValueFactory( d -> new SimpleStringProperty(d.getValue().getDetalles()));

        vehiculosTable.setItems(vehiculos);

        // Al seleccionar una fila, carga el vehículo en el formulario para editarlo
        vehiculosTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, anterior, actual) -> {
                    if (actual != null) cargarEnEdicion(actual);
                });
    }

    private void ocultarPaneles() {
        setPanelVisible(camionPane,       false);
        setPanelVisible(automovilPane,    false);
        setPanelVisible(busMunicipalPane, false);
        setPanelVisible(busInterPane,     false);
    }

    // ════════════════════════════════════════════════════════════════════
    //  HANDLERS DE EVENTOS (enlazados desde el FXML)
    // ════════════════════════════════════════════════════════════════════

    /** Muestra el panel de campos específicos según el tipo seleccionado. */
    @FXML
    private void onTipoChanged() {
        String tipo = tipoCombo.getValue();
        ocultarPaneles();
        if (tipo == null) return;

        switch (tipo) {
            case CAMION             -> setPanelVisible(camionPane,       true);
            case AUTOMOVIL          -> setPanelVisible(automovilPane,    true);
            case BUS_MUNICIPAL      -> setPanelVisible(busMunicipalPane, true);
            case BUS_INTERMUNICIPAL -> setPanelVisible(busInterPane,     true);
        }
    }

    /** Crea un nuevo vehículo y lo agrega a la lista. */
    @FXML
    private void onAgregar() {
        if (!camposValidos()) return;
        Vehiculo v = construirVehiculo();
        if (v == null) return;

        vehiculos.add(v);
        refrescarContador();
        limpiarFormulario();
        mostrarEstado("Vehículo agregado exitosamente.", "success");
    }

    /** Actualiza el vehículo seleccionado con los datos del formulario. */
    @FXML
    private void onActualizar() {
        if (vehiculoEnEdicion == null) {
            mostrarEstado("Seleccione un vehículo de la tabla para editar.", "warning");
            return;
        }
        if (!camposValidos()) return;
        Vehiculo v = construirVehiculo();
        if (v == null) return;

        int idx = vehiculos.indexOf(vehiculoEnEdicion);
        vehiculos.set(idx, v);
        refrescarContador();
        limpiarFormulario();
        mostrarEstado("Vehículo actualizado exitosamente.", "success");
    }

    /** Elimina el vehículo seleccionado en la tabla tras confirmación. */
    @FXML
    private void onEliminar() {
        Vehiculo sel = vehiculosTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            mostrarEstado("Seleccione un vehículo de la tabla para eliminar.", "warning");
            return;
        }

        Alert confirm = new Alert(
                Alert.AlertType.CONFIRMATION,
                "¿Desea eliminar el vehículo con placa \"" + sel.getPlaca() + "\"?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);

        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                vehiculos.remove(sel);
                refrescarContador();
                limpiarFormulario();
                mostrarEstado("Vehículo eliminado.", "success");
            }
        });
    }

    /** Resetea el formulario sin afectar la tabla. */
    @FXML
    private void onLimpiar() {
        limpiarFormulario();
        mostrarEstado("", "");
    }

    // ════════════════════════════════════════════════════════════════════
    //  LÓGICA PRIVADA
    // ════════════════════════════════════════════════════════════════════

    /**
     * Construye el objeto Vehiculo correcto según el tipo seleccionado
     * y los valores del formulario.
     */
    private Vehiculo construirVehiculo() {
        String tipo   = tipoCombo.getValue();
        String marca  = marcaField.getText().trim();
        String color  = colorField.getText().trim();
        String placa  = placaField.getText().trim();
        String modelo = modeloField.getText().trim();

        try {
            return switch (tipo) {

                case CAMION -> {
                    if (!validarCamion()) yield null;
                    double cap = Double.parseDouble(capacidadField.getText().trim());
                    yield new Camion(marca, color, placa, modelo, cap,
                                     tipoCargaField.getText().trim());
                }

                case AUTOMOVIL -> {
                    if (!validarAutomovil()) yield null;
                    yield new Automovil(marca, color, placa, modelo,
                                        puertasSpinner.getValue(),
                                        disposicionCombo.getValue());
                }

                case BUS_MUNICIPAL -> {
                    if (!validarBusMunicipal()) yield null;
                    yield new BusMunicipal(marca, color, placa, modelo,
                                           empresaMunField.getText().trim(),
                                           choferMunField.getText().trim(),
                                           rutaField.getText().trim());
                }

                case BUS_INTERMUNICIPAL -> {
                    if (!validarBusIntermunicipal()) yield null;
                    yield new BusIntermunicipal(marca, color, placa, modelo,
                                                empresaInterField.getText().trim(),
                                                choferInterField.getText().trim(),
                                                ciudadOrigenField.getText().trim(),
                                                ciudadDestinoField.getText().trim());
                }

                default -> null;
            };

        } catch (NumberFormatException e) {
            mostrarEstado("La capacidad debe ser un número válido (ej: 5.5).", "error");
            return null;
        }
    }

    /** Carga los datos de un vehículo en el formulario para su edición. */
    private void cargarEnEdicion(Vehiculo v) {
        vehiculoEnEdicion = v;

        // Campos comunes
        tipoCombo.setValue(v.getTipo());
        onTipoChanged();
        marcaField.setText(v.getMarca());
        colorField.setText(v.getColor());
        placaField.setText(v.getPlaca());
        modeloField.setText(v.getModelo());

        // Campos específicos (pattern matching Java 16+)
        if (v instanceof Camion c) {
            capacidadField.setText(String.valueOf(c.getCapacidad()));
            tipoCargaField.setText(c.getTipoCarga());

        } else if (v instanceof Automovil a) {
            puertasSpinner.getValueFactory().setValue(a.getNumeroPuertas());
            disposicionCombo.setValue(a.getDisposicion());

        } else if (v instanceof BusMunicipal bm) {
            empresaMunField.setText(bm.getEmpresa());
            choferMunField.setText(bm.getChofer());
            rutaField.setText(bm.getRuta());

        } else if (v instanceof BusIntermunicipal bi) {
            empresaInterField.setText(bi.getEmpresa());
            choferInterField.setText(bi.getChofer());
            ciudadOrigenField.setText(bi.getCiudadOrigen());
            ciudadDestinoField.setText(bi.getCiudadDestino());
        }

        agregarBtn.setDisable(true);
        actualizarBtn.setDisable(false);
        mostrarEstado("Editando placa: " + v.getPlaca(), "warning");
    }

    /** Resetea todos los campos y vuelve al modo de creación. */
    private void limpiarFormulario() {
        tipoCombo.setValue(null);
        marcaField.clear();
        colorField.clear();
        placaField.clear();
        modeloField.clear();

        capacidadField.clear();
        tipoCargaField.clear();
        puertasSpinner.getValueFactory().setValue(4);
        disposicionCombo.setValue(null);

        empresaMunField.clear();
        choferMunField.clear();
        rutaField.clear();

        empresaInterField.clear();
        choferInterField.clear();
        ciudadOrigenField.clear();
        ciudadDestinoField.clear();

        ocultarPaneles();
        vehiculosTable.getSelectionModel().clearSelection();
        vehiculoEnEdicion = null;
        agregarBtn.setDisable(false);
        actualizarBtn.setDisable(true);
    }

    // ── Validaciones ──────────────────────────────────────────────────────

    private boolean camposValidos() {
        if (tipoCombo.getValue() == null) {
            mostrarEstado("Seleccione el tipo de vehículo.", "error");
            return false;
        }
        if (vacio(marcaField) || vacio(colorField) || vacio(placaField) || vacio(modeloField)) {
            mostrarEstado("Complete todos los campos de Información General (marcados con *).", "error");
            return false;
        }
        return true;
    }

    private boolean validarCamion() {
        if (vacio(capacidadField) || vacio(tipoCargaField)) {
            mostrarEstado("Complete los datos del camión.", "error");
            return false;
        }
        return true;
    }

    private boolean validarAutomovil() {
        if (disposicionCombo.getValue() == null) {
            mostrarEstado("Seleccione la disposición del automóvil.", "error");
            return false;
        }
        return true;
    }

    private boolean validarBusMunicipal() {
        if (vacio(empresaMunField) || vacio(choferMunField) || vacio(rutaField)) {
            mostrarEstado("Complete los datos del bus municipal.", "error");
            return false;
        }
        return true;
    }

    private boolean validarBusIntermunicipal() {
        if (vacio(empresaInterField) || vacio(choferInterField)
                || vacio(ciudadOrigenField) || vacio(ciudadDestinoField)) {
            mostrarEstado("Complete los datos del bus intermunicipal.", "error");
            return false;
        }
        return true;
    }

    // ── Utilidades ────────────────────────────────────────────────────────

    private boolean vacio(TextField f) {
        return f.getText() == null || f.getText().trim().isEmpty();
    }

    private void setPanelVisible(VBox panel, boolean visible) {
        panel.setVisible(visible);
        panel.setManaged(visible);   // false = no ocupa espacio en el layout
    }

    private void refrescarContador() {
        int n = vehiculos.size();
        countLabel.setText("Total: " + n + " vehículo" + (n != 1 ? "s" : ""));
    }

    private void mostrarEstado(String mensaje, String tipo) {
        statusLabel.setText(mensaje);
        statusLabel.getStyleClass().removeAll("status-success", "status-error", "status-warning");
        if (!tipo.isEmpty()) {
            statusLabel.getStyleClass().add("status-" + tipo);
        }
    }
}
