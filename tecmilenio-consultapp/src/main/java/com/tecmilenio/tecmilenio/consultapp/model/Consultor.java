/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.model;

/**
 *
 * @author hevergonzalez
 */
public class Consultor {
    private String id;
    private String nombre;
    private String especialidad;

    public Consultor() {}
    public Consultor(String id, String nombre, String especialidad) {
        this.id = id; this.nombre = nombre; this.especialidad = especialidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @Override public String toString() {
        return "Consultor{id='%s', nombre='%s', especialidad='%s'}".formatted(id, nombre, especialidad);
    }
}