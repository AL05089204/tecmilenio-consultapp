/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.service;
import com.tecmilenio.tecmilenio.consultapp.model.Cita;
import com.tecmilenio.tecmilenio.consultapp.model.Cliente;
import com.tecmilenio.tecmilenio.consultapp.model.Consultor;
import com.tecmilenio.tecmilenio.consultapp.repo.CitaRepo;
import com.tecmilenio.tecmilenio.consultapp.repo.ClienteRepo;
import com.tecmilenio.tecmilenio.consultapp.repo.ConsultorRepo;
import com.tecmilenio.tecmilenio.consultapp.util.DateUtil;
/**
 *
 * @author hevergonzalez
 */

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AgendaService {
    private final ClienteRepo clienteRepo;
    private final ConsultorRepo consultorRepo;
    private final CitaRepo citaRepo;

    public AgendaService(ClienteRepo clienteRepo, ConsultorRepo consultorRepo, CitaRepo citaRepo) {
        this.clienteRepo = clienteRepo;
        this.consultorRepo = consultorRepo;
        this.citaRepo = citaRepo;
    }

    public Cliente altaCliente(String nombre, String email) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre requerido");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email inválido");
        var c = new Cliente(IdGenerator.nuevoId(), nombre, email);
        return clienteRepo.guardar(c);
    }

    public Consultor altaConsultor(String nombre, String especialidad) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre requerido");
        if (especialidad == null || especialidad.isBlank()) especialidad = "General";
        var c = new Consultor(IdGenerator.nuevoId(), nombre, especialidad);
        return consultorRepo.guardar(c);
    }

    public List<Cliente> listarClientes() { return clienteRepo.listar(); }
    public List<Consultor> listarConsultores() { return consultorRepo.listar(); }
    public List<Cita> listarCitas() { return citaRepo.listar(); }

    public Cita crearCita(String clienteId, String consultorId, LocalDateTime inicio, LocalDateTime fin, String desc) {
        validarExistencia(clienteId, consultorId);
        validarHorario(inicio, fin);
        validarConflicto(null, consultorId, inicio, fin);

        var cita = new Cita(IdGenerator.nuevoId(), clienteId, consultorId, inicio, fin, desc);
        return citaRepo.guardar(cita);
    }

    public Cita reprogramarCita(String citaId, LocalDateTime nuevoInicio, LocalDateTime nuevoFin) {
        var cita = citaRepo.buscarPorId(citaId).orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        validarHorario(nuevoInicio, nuevoFin);
        validarConflicto(citaId, cita.getConsultorId(), nuevoInicio, nuevoFin);
        cita.setInicio(nuevoInicio);
        cita.setFin(nuevoFin);
        return citaRepo.guardar(cita);
    }

    public void cancelarCita(String citaId) {
        var cita = citaRepo.buscarPorId(citaId).orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
        cita.cancelar();
        citaRepo.guardar(cita);
    }

    private void validarExistencia(String clienteId, String consultorId) {
        if (clienteRepo.buscarPorId(clienteId).isEmpty()) throw new IllegalArgumentException("Cliente inexistente");
        if (consultorRepo.buscarPorId(consultorId).isEmpty()) throw new IllegalArgumentException("Consultor inexistente");
    }

    private void validarHorario(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null) throw new IllegalArgumentException("Fechas requeridas");
        if (!fin.isAfter(inicio)) throw new IllegalArgumentException("Fin debe ser posterior a inicio");
    }

    private void validarConflicto(String citaId, String consultorId, LocalDateTime inicio, LocalDateTime fin) {
        for (var c : citaRepo.listar()) {
            if (!Objects.equals(c.getConsultorId(), consultorId)) continue;
            if (c.getEstado() == Cita.Estado.CANCELADA) continue;
            if (citaId != null && c.getId().equals(citaId)) continue;
            boolean seEmpalma = !(fin.isEqual(c.getInicio()) || fin.isBefore(c.getInicio())
                               || inicio.isEqual(c.getFin()) || inicio.isAfter(c.getFin()));
            if (seEmpalma) {
                throw new IllegalArgumentException(
                        "Conflicto: el consultor ya tiene una cita entre %s y %s"
                                .formatted(DateUtil.format(c.getInicio()), DateUtil.format(c.getFin())));
            }
        }
    }

    public void guardarTodo() {
        clienteRepo.persistir();
        consultorRepo.persistir();
        citaRepo.persistir();
    }
}
