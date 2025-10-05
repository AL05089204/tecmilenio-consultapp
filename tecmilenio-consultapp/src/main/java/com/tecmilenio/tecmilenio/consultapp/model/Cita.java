/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.model;

/**
 *
 * @author hevergonzalez
 */

import java.time.LocalDateTime;

public class Cita {
    private String id;
    private String clienteId;
    private String consultorId;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String descripcion;
    private Estado estado = Estado.ACTIVA;

    public enum Estado { ACTIVA, CANCELADA }

    public Cita() {}

    public Cita(String id, String clienteId, String consultorId,
                LocalDateTime inicio, LocalDateTime fin, String descripcion) {
        this.id = id; this.clienteId = clienteId; this.consultorId = consultorId;
        this.inicio = inicio; this.fin = fin; this.descripcion = descripcion;
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public String getConsultorId() { return consultorId; }
    public LocalDateTime getInicio() { return inicio; }
    public LocalDateTime getFin() { return fin; }
    public String getDescripcion() { return descripcion; }
    public Estado getEstado() { return estado; }

    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }
    public void setFin(LocalDateTime fin) { this.fin = fin; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void cancelar() { this.estado = Estado.CANCELADA; }

    @Override public String toString() {
        return "Cita{id='%s', clienteId='%s', consultorId='%s', inicio=%s, fin=%s, estado=%s, desc='%s'}"
                .formatted(id, clienteId, consultorId, inicio, fin, estado, descripcion);
    }
}

