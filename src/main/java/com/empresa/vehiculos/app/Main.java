package com.empresa.vehiculos.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación JavaFX.
 * Carga MainView.fxml y configura la ventana principal.
 *
 * Para ejecutar con Maven:
 *   mvn javafx:run
 *
 * Para ejecutar desde un IDE sin module-info:
 *   Agregar VM args:
 *   --module-path <ruta-javafx-lib> --add-modules javafx.controls,javafx.fxml
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/empresa/vehiculos/view/MainView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Sistema de Gestion de Vehiculos xx");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(620);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
