package com.example.reservas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_management";
    private static final String USER = "root"; // Cambia esto a tu usuario de MySQL
    private static final String PASSWORD = ""; // Cambia esto a tu contraseña de MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


