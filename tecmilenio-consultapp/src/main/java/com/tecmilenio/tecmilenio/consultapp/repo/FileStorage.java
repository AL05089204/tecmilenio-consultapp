/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.repo;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author hevergonzalez
 */
public class FileStorage {
    private final Path base;

    public FileStorage(String carpeta) {
        this.base = Paths.get(System.getProperty("user.dir")).resolve(carpeta);
        try { Files.createDirectories(base); } catch (IOException ignored) {}
    }

    public <T> void escribirLista(String nombre, List<T> lista) {
        Path p = base.resolve(nombre);
        String json = toJsonArray(lista);
        try { Files.writeString(p, json); } catch (IOException e) { throw new RuntimeException(e); }
    }

    public <T> List<T> leerLista(String nombre, Class<T[]> arrayType) {
        Path p = base.resolve(nombre);
        if (!Files.exists(p)) return new ArrayList<>();
        try {
            String json = Files.readString(p);
            T[] arr = fromJsonArray(json, arrayType);
            return Arrays.stream(arr == null ? (T[]) Array.newInstance(arrayType.getComponentType(), 0) : arr)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ===== JSON MUY BÁSICO (solo strings, números y campos simples) =====
    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String quote(String s) {
        return s == null ? "null" : "\"" + escape(s) + "\"";
    }

    private static String toJson(Object o) {
        // Usamos reflexión básica
        if (o == null) return "null";
        var cls = o.getClass();
        var campos = cls.getDeclaredFields();
        List<String> pares = new ArrayList<>();
        for (var f : campos) {
            f.setAccessible(true);
            try {
                Object v = f.get(o);
                String val;
                if (v == null) val = "null";
                else if (v instanceof Number || v instanceof Boolean) val = v.toString();
                else val = quote(String.valueOf(v));
                pares.add("\"" + f.getName() + "\":" + val);
            } catch (IllegalAccessException ignored) {}
        }
        return "{" + String.join(",", pares) + "}";
    }

    private static <T> String toJsonArray(List<T> lista) {
        return "[" + lista.stream().map(FileStorage::toJson).collect(Collectors.joining(",")) + "]";
    }

    private static <T> T[] fromJsonArray(String json, Class<T[]> arrayType) {
        // Implementación mínima: parseo “ingenuo” por objetos { ... } separados
        json = json.trim();
        if (json.isEmpty() || json.equals("[]")) {
            return (T[]) Array.newInstance(arrayType.getComponentType(), 0);
        }
        List<String> objetos = new ArrayList<>();
        int nivel = 0, start = 0;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') { if (nivel == 0) start = i; nivel++; }
            else if (c == '}') { nivel--; if (nivel == 0) objetos.add(json.substring(start, i + 1)); }
        }
        try {
            @SuppressWarnings("unchecked")
            T[] arr = (T[]) Array.newInstance(arrayType.getComponentType(), objetos.size());
            for (int i = 0; i < objetos.size(); i++) {
                arr[i] = JsonMapper.mapear(objetos.get(i), (Class<T>) arrayType.getComponentType());
            }
            return arr;
        } catch (Exception e) {
            return null;
        }
    }

    // Mapper muy simple a POJO usando reflexión
    static class JsonMapper {
        static <T> T mapear(String objJson, Class<T> type) {
            try {
                T inst = type.getDeclaredConstructor().newInstance();
                String cuerpo = objJson.substring(1, objJson.length() - 1).trim();
                // pares "campo":valor
                String[] pares = cuerpo.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for (String par : pares) {
                    if (par.isBlank()) continue;
                    String[] kv = par.split(":", 2);
                    String k = kv[0].trim().replaceAll("^\"|\"$", "");
                    String v = kv[1].trim();
                    var f = type.getDeclaredField(k);
                    f.setAccessible(true);
                    if (v.equals("null")) { f.set(inst, null); continue; }
                    Class<?> ft = f.getType();
                    if (ft == String.class) {
                        f.set(inst, v.replaceAll("^\"|\"$", "").replace("\\\"", "\"").replace("\\\\", "\\"));
                    } else if (ft == int.class || ft == Integer.class) {
                        f.set(inst, Integer.parseInt(v));
                    } else if (ft == long.class || ft == Long.class) {
                        f.set(inst, Long.parseLong(v));
                    } else if (ft == boolean.class || ft == Boolean.class) {
                        f.set(inst, Boolean.parseBoolean(v));
                    } else {
                        // Para tipos no soportados aquí (ej. LocalDateTime) los dejamos nulos; el Service los recalcula si aplica
                        // En nuestro modelo, LocalDateTime se setea desde Service al reconstruir cita si fuera necesario.
                        // Pero como aquí persistimos toString(), lo rehidratamos luego en el Service si detecta cadenas.
                        f.set(inst, v.replaceAll("^\"|\"$", ""));
                    }
                }
                return inst;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
