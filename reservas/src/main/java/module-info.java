module com.example.reservas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.reservas to javafx.fxml;
    exports com.example.reservas;
}