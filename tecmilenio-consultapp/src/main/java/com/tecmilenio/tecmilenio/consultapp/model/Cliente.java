/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.model;

/**
 *
 * @author hevergonzalez
 */
public class Cliente {
    private String id;
    private String nombre;
    private String email;

    public Cliente() {}
    public Cliente(String id, String nombre, String email) {
        this.id = id; this.nombre = nombre; this.email = email;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }

    @Override public String toString() {
        return "Cliente{id='%s', nombre='%s', email='%s'}".formatted(id, nombre, email);
    }
}
