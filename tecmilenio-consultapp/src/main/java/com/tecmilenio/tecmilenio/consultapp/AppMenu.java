/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp;

/**
 *
 * @author hevergonzalez
 */
import com.tecmilenio.tecmilenio.consultapp.model.Cita;
import com.tecmilenio.tecmilenio.consultapp.model.Cliente;
import com.tecmilenio.tecmilenio.consultapp.model.Consultor;
import com.tecmilenio.tecmilenio.consultapp.repo.CitaRepo;
import com.tecmilenio.tecmilenio.consultapp.repo.ClienteRepo;
import com.tecmilenio.tecmilenio.consultapp.repo.ConsultorRepo;
import com.tecmilenio.tecmilenio.consultapp.repo.FileStorage;
import com.tecmilenio.tecmilenio.consultapp.service.AgendaService;
import com.tecmilenio.tecmilenio.consultapp.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AppMenu {
    private final AgendaService service;
    private final Scanner sc = new Scanner(System.in);

    public AppMenu() {
        var storage = new FileStorage("consultapp_data");
        var clienteRepo = new ClienteRepo(storage);
        var consultorRepo = new ConsultorRepo(storage);
        var citaRepo = new CitaRepo(storage);
        this.service = new AgendaService(clienteRepo, consultorRepo, citaRepo);
    }

    public void iniciar() {
        System.out.println("=== CONSULTAPP ===");
        boolean salir = false;
        while (!salir) {
            System.out.println("""
                    \nMenú:
                    1) Alta Cliente
                    2) Alta Consultor
                    3) Listar Clientes
                    4) Listar Consultores
                    5) Crear Cita
                    6) Listar Citas
                    7) Reprogramar Cita
                    8) Cancelar Cita
                    9) Guardar
                    0) Salir
                    """);
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1" -> altaCliente();
                    case "2" -> altaConsultor();
                    case "3" -> listarClientes();
                    case "4" -> listarConsultores();
                    case "5" -> crearCita();
                    case "6" -> listarCitas();
                    case "7" -> reprogramarCita();
                    case "8" -> cancelarCita();
                    case "9" -> guardar();
                    case "0" -> { guardar(); salir = true; }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        System.out.println("¡Hasta luego!");
    }

    private void altaCliente() {
        System.out.print("Nombre cliente: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        Cliente c = service.altaCliente(nombre, email);
        System.out.println("Cliente creado: " + c);
    }

    private void altaConsultor() {
        System.out.print("Nombre consultor: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Especialidad: ");
        String esp = sc.nextLine().trim();
        Consultor c = service.altaConsultor(nombre, esp);
        System.out.println("Consultor creado: " + c);
    }

    private void listarClientes() {
        service.listarClientes().forEach(System.out::println);
    }

    private void listarConsultores() {
        service.listarConsultores().forEach(System.out::println);
    }

    private void crearCita() {
        System.out.print("ID Cliente: ");
        String clienteId = sc.nextLine().trim();
        System.out.print("ID Consultor: ");
        String consultorId = sc.nextLine().trim();
        System.out.print("Inicio (yyyy-MM-dd HH:mm): ");
        LocalDateTime inicio = DateUtil.parse(sc.nextLine().trim());
        System.out.print("Fin (yyyy-MM-dd HH:mm): ");
        LocalDateTime fin = DateUtil.parse(sc.nextLine().trim());
        System.out.print("Descripción: ");
        String desc = sc.nextLine().trim();
        Cita cita = service.crearCita(clienteId, consultorId, inicio, fin, desc);
        System.out.println("Cita creada: " + cita);
    }

    private void listarCitas() {
        List<Cita> citas = service.listarCitas();
        if (citas.isEmpty()) System.out.println("Sin citas.");
        else citas.forEach(System.out::println);
    }

    private void reprogramarCita() {
        System.out.print("ID Cita: ");
        String citaId = sc.nextLine().trim();
        System.out.print("Nuevo inicio (yyyy-MM-dd HH:mm): ");
        LocalDateTime inicio = DateUtil.parse(sc.nextLine().trim());
        System.out.print("Nuevo fin (yyyy-MM-dd HH:mm): ");
        LocalDateTime fin = DateUtil.parse(sc.nextLine().trim());
        Cita c = service.reprogramarCita(citaId, inicio, fin);
        System.out.println("Cita reprogramada: " + c);
    }

    private void cancelarCita() {
        System.out.print("ID Cita: ");
        String citaId = sc.nextLine().trim();
        service.cancelarCita(citaId);
        System.out.println("Cita cancelada.");
    }

    private void guardar() {
        service.guardarTodo();
        System.out.println("Datos guardados.");
    }
}