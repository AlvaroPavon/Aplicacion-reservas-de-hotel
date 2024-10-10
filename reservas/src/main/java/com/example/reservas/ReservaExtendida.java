package com.example.reservas;

import java.time.LocalDate;

public class ReservaExtendida {
    private Reserva reserva;
    private String nombreCliente;

    public ReservaExtendida(Reserva reserva, String nombreCliente) {
        this.reserva = reserva;
        this.nombreCliente = nombreCliente;
    }

    public int getIdreserva() {
        return reserva.getIdreserva();
    }

    public int getIdhabitacion() {
        return reserva.getIdhabitacion();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDate getFechaIngreso() {
        return reserva.getFechaIngreso();
    }

    public LocalDate getFechaSalida() {
        return reserva.getFechaSalida();
    }

    public String getEstado() {
        return reserva.getEstado();
    }
}
