/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.repo;

/**
 *
 * @author hevergonzalez
 */
import java.util.List;
import java.util.Optional;

public interface Repositorio<T> {
    T guardar(T entidad);
    Optional<T> buscarPorId(String id);
    List<T> listar();
    void reemplazarTodo(List<T> entidades);
}
