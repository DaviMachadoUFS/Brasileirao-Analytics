/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brasileirao.analytic.model;

/**
 *
 * @author davim
 */
public class Partida {
    private String mandante;
    private String visitante;
    private String vencedor;
    private int placarMandante;
    private int placarVisitante;
    private String vencedorEstado;

    public Partida(String mandante, String visitante, String vencedor, int placarMandante, int placarVisitante, String vencedorEstado) {
        this.mandante = mandante;
        this.visitante = visitante;
        this.vencedor = vencedor;
        this.placarMandante = placarMandante;
        this.placarVisitante = placarVisitante;
        this.vencedorEstado = vencedorEstado;
    }

    public String getMandante() {
        return mandante;
    }

    public String getVisitante() {
        return visitante;
    }

    public String getVencedor() {
        return vencedor;
    }

    public int getPlacarMandante() {
        return placarMandante;
    }

    public int getPlacarVisitante() {
        return placarVisitante;
    }

    public String getVencedorEstado() {
        return vencedorEstado;
    }
}
