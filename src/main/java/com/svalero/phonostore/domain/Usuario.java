package com.svalero.phonostore.domain;

public class Usuario {
    Integer idUsuario;
    String nombreUsuario;
    String contraseña;
    String nombre;
    String apellidos1;
    String apellidos2;
    String telefono;
    String email;
    String rol;

    public Usuario(){

    }

    public Usuario(Integer idUsuario, String nombreUsuario, String contraseña, String nombre, String apellidos1, String apellidos2, String telefono, String email, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellidos1 = apellidos1;
        this.apellidos2 = apellidos2;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos1() {
        return apellidos1;
    }

    public void setApellidos1(String apellidos1) {
        this.apellidos1 = apellidos1;
    }

    public String getApellidos2() {
        return apellidos2;
    }

    public void setApellidos2(String apellidos2) {
        this.apellidos2 = apellidos2;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "NombreUsuario: " + nombreUsuario +
                ", Nombre: " + nombre +
                ", Apellidos1: " + apellidos1 +
                ", Apellidos2: " + apellidos2 +
                ", Telefono: " + telefono;
    }
}
