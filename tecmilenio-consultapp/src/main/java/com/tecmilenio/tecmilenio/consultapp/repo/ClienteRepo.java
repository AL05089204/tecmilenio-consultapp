/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.repo;
import com.tecmilenio.tecmilenio.consultapp.model.Cliente;
/**
 *
 * @author hevergonzalez
 */

import java.util.*;

public class ClienteRepo implements Repositorio<Cliente> {
    private final Map<String, Cliente> datos = new LinkedHashMap<>();
    private final FileStorage storage;
    private final String key = "clientes.json";

    public ClienteRepo(FileStorage storage) {
        this.storage = storage;
        storage.leerLista(key, Cliente[].class).forEach(c -> datos.put(c.getId(), c));
    }

    @Override public Cliente guardar(Cliente entidad) {
        datos.put(entidad.getId(), entidad);
        return entidad;
    }

    @Override public Optional<Cliente> buscarPorId(String id) { return Optional.ofNullable(datos.get(id)); }

    @Override public List<Cliente> listar() { return new ArrayList<>(datos.values()); }

    @Override public void reemplazarTodo(List<Cliente> entidades) {
        datos.clear();
        entidades.forEach(c -> datos.put(c.getId(), c));
    }

    public void persistir() { storage.escribirLista(key, listar()); }
}
