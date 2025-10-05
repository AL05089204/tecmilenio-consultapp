/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.repo;
import com.tecmilenio.tecmilenio.consultapp.model.Consultor;
/**
 *
 * @author hevergonzalez
 */

import java.util.*;

public class ConsultorRepo implements Repositorio<Consultor> {
    private final Map<String, Consultor> datos = new LinkedHashMap<>();
    private final FileStorage storage;
    private final String key = "consultores.json";

    public ConsultorRepo(FileStorage storage) {
        this.storage = storage;
        storage.leerLista(key, Consultor[].class).forEach(c -> datos.put(c.getId(), c));
    }

    @Override public Consultor guardar(Consultor entidad) {
        datos.put(entidad.getId(), entidad);
        return entidad;
    }

    @Override public Optional<Consultor> buscarPorId(String id) { return Optional.ofNullable(datos.get(id)); }

    @Override public List<Consultor> listar() { return new ArrayList<>(datos.values()); }

    @Override public void reemplazarTodo(List<Consultor> entidades) {
        datos.clear();
        entidades.forEach(c -> datos.put(c.getId(), c));
    }

    public void persistir() { storage.escribirLista(key, listar()); }
}
