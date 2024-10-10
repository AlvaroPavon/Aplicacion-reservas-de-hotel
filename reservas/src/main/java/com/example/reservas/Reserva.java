package com.example.reservas;

import java.time.LocalDate;

public class Reserva {
    private int idreserva;
    private int idhabitacion;
    private int idcliente;
    // Eliminamos el campo idtrabajador
    private String tipoReserva;
    private LocalDate fechaReserva;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private double costoTotal;
    private String observacion;
    private String estado;

    public Reserva(int idhabitacion, int idcliente, String tipoReserva, LocalDate fechaReserva, LocalDate fechaIngreso, LocalDate fechaSalida, double costoTotal, String observacion, String estado) {
        this.idhabitacion = idhabitacion;
        this.idcliente = idcliente;
        this.tipoReserva = tipoReserva;
        this.fechaReserva = fechaReserva;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.costoTotal = costoTotal;
        this.observacion = observacion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

