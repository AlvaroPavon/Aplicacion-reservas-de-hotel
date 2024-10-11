package com.example.reservas;/*Realizado por Alvaro Pavon Martinez */

public class Cliente {
    private int idpersona;
    private String nombre;
    private String apellido;
    private String amaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String direccion;
    private String telefono;
    private String email;
    private String estado;
    private String codigoCliente;

    public Cliente(String nombre, String apellido, String amaterno, String tipoDocumento, String numeroDocumento, String direccion, String telefono, String email, String estado, String codigoCliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.amaterno = amaterno;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.codigoCliente = codigoCliente;
    }

    // Getters y Setters
    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " " + amaterno;

    }
}









































/*Realizado por Alvaro Pavon Martinez */
