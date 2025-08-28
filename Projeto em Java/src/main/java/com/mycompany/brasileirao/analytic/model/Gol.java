/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brasileirao.analytic.model;

/**
 *
 * @author davim
 */
public class Gol {
    private String time;
    private String atleta;
    private String tipo;

    public Gol(String time, String atleta, String tipo) {
        this.time = time;
        this.atleta = atleta;
        this.tipo = tipo;
    }

    public String getTime() {
        return time;
    }

    public String getAtleta() {
        return atleta;
    }

    public String getTipo() {
        return tipo;
    }
}
