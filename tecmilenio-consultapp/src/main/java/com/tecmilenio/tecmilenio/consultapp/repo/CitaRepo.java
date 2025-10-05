/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.repo;
import com.tecmilenio.tecmilenio.consultapp.model.Cita;
/**
 *
 * @author hevergonzalez
 */

import java.util.*;

public class CitaRepo implements Repositorio<Cita> {
    private final Map<String, Cita> datos = new LinkedHashMap<>();
    private final FileStorage storage;
    private final String key = "citas.json";

    public CitaRepo(FileStorage storage) {
        this.storage = storage;
        storage.leerLista(key, Cita[].class).forEach(c -> datos.put(c.getId(), c));
    }

    @Override public Cita guardar(Cita entidad) {
        datos.put(entidad.getId(), entidad);
        return entidad;
    }

    @Override public Optional<Cita> buscarPorId(String id) { return Optional.ofNullable(datos.get(id)); }

    @Override public List<Cita> listar() { return new ArrayList<>(datos.values()); }

    @Override public void reemplazarTodo(List<Cita> entidades) {
        datos.clear();
        entidades.forEach(c -> datos.put(c.getId(), c));
    }

    public void persistir() { storage.escribirLista(key, listar()); }
}
