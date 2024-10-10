module com.example.reservas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Abre el paquete que contiene las vistas para JavaFX
    opens com.example.reservas.view to javafx.fxml;

    // Exporta el paquete principal
    exports com.example.reservas;
    opens com.example.reservas to javafx.fxml;
    exports com.example.reservas.view;
}
