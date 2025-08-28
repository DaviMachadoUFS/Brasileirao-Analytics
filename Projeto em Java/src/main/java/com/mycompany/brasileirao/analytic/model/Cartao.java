/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brasileirao.analytic.model;

/**
 *
 * @author davim
 */
public class Cartao {
    private String time;
    private String tipo;
    private String atleta;

    public Cartao(String time, String tipo, String atleta) {
        this.time = time;
        this.tipo = tipo;
        this.atleta = atleta;
    }

    public String getTime() {
        return time;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAtleta() {
        return atleta;
    }
}
