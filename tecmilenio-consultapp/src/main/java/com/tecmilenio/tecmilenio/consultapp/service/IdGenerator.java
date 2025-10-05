/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecmilenio.tecmilenio.consultapp.service;
import java.util.UUID;
/**
 *
 * @author hevergonzalez
 */


public class IdGenerator {
    public static String nuevoId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
