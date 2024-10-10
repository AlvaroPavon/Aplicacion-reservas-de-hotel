package com.example.reservas;

import java.time.LocalDate;

public class Comprobante {
    private int idcomprobante;
    private int idreserva;
    private String tipoComprobante;
    private String numComprobante;
    private double igv;
    private double costoConsumo;
    private double costoReserva;
    private LocalDate fechaEmision;
    private LocalDate fechaPago;
    private String estado;

    // Constructor
    public Comprobante(int idreserva, String tipoComprobante, String numComprobante, double igv, double costoConsumo, double costoReserva, LocalDate fechaEmision, LocalDate fechaPago, String estado) {
        this.idreserva = idreserva;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.igv = igv;
        this.costoConsumo = costoConsumo;
        this.costoReserva = costoReserva;
        this.fechaEmision = fechaEmision;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(int idcomprobante) {
        this.idcomprobante = idcomprobante;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getCostoConsumo() {
        return costoConsumo;
    }

    public void setCostoConsumo(double costoConsumo) {
        this.costoConsumo = costoConsumo;
    }

    public double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

