module com.ucv.lab06 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    // Paquetes del proyecto original

    // Paquetes del Sistema de Gestion de Vehiculos (MVC)
    opens com.empresa.vehiculos.controller to javafx.fxml;
    exports com.empresa.vehiculos.app;
    opens com.empresa.vehiculos.app to javafx.fxml, javafx.graphics;
}