/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brasileirao.analytic.controller;
import java.text.Collator;
import com.mycompany.brasileirao.analytic.model.Partida;
import com.mycompany.brasileirao.analytic.model.Gol;
import com.mycompany.brasileirao.analytic.model.Cartao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author davim
 */
public class ProcessadorDados {

    private static final Map<String, String> estadoParaRegiao = new HashMap<>();

    static {
        estadoParaRegiao.put("SP", "Sudeste");
        estadoParaRegiao.put("RJ", "Sudeste");
        estadoParaRegiao.put("MG", "Sudeste");
        estadoParaRegiao.put("ES", "Sudeste");
        
        estadoParaRegiao.put("RS", "Sul");
        estadoParaRegiao.put("PR", "Sul");
        estadoParaRegiao.put("SC", "Sul");
        
        estadoParaRegiao.put("BA", "Nordeste");
        estadoParaRegiao.put("PE", "Nordeste");
        estadoParaRegiao.put("CE", "Nordeste");
        estadoParaRegiao.put("RN", "Nordeste");
        estadoParaRegiao.put("AL", "Nordeste");
        estadoParaRegiao.put("SE", "Nordeste");
        estadoParaRegiao.put("MA", "Nordeste");
        estadoParaRegiao.put("PB", "Nordeste");
        
        estadoParaRegiao.put("GO", "Centro-Oeste");
        estadoParaRegiao.put("MT", "Centro-Oeste");
        estadoParaRegiao.put("MS", "Centro-Oeste");
        estadoParaRegiao.put("DF", "Centro-Oeste");
        
        estadoParaRegiao.put("PA", "Norte");
        estadoParaRegiao.put("AM", "Norte");
        estadoParaRegiao.put("AC", "Norte");
        estadoParaRegiao.put("AP", "Norte");
        estadoParaRegiao.put("TO", "Norte");
        estadoParaRegiao.put("RO", "Norte");
        estadoParaRegiao.put("RR", "Norte");
    }

    public Map<String, Integer> contarVitoriasPorRegiao(List<Partida> partidas) {
        Map<String, Integer> vitoriasPorRegiao = new HashMap<>();
        estadoParaRegiao.values().forEach(regiao -> vitoriasPorRegiao.put(regiao, 0));
        
        for (Partida partida : partidas) {
            String estadoVencedor = partida.getVencedorEstado();
            if (estadoVencedor != null) {
                String regiao = estadoParaRegiao.get(estadoVencedor);
                if (regiao != null) {
                    vitoriasPorRegiao.put(regiao, vitoriasPorRegiao.get(regiao) + 1);
                }
            }
        }
        return vitoriasPorRegiao;
    }

    public List<String> obterNomesAtletas(List<Gol> gols, List<Cartao> cartoes) {
        Set<String> nomesAtletasUnicos = new HashSet<>();
        for (Gol gol : gols) {
            nomesAtletasUnicos.add(gol.getAtleta());
        }
        for (Cartao cartao : cartoes) {
            nomesAtletasUnicos.add(cartao.getAtleta());
        }
        List<String> listaNomes = new ArrayList<>(nomesAtletasUnicos);
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        Collections.sort(listaNomes, collator);
        
        return listaNomes;
    }
    
    public List<String> obterNomesClubes(List<Partida> partidas) {
        Set<String> nomesClubesUnicos = new HashSet<>();
        for (Partida partida : partidas) {
            nomesClubesUnicos.add(partida.getMandante());
            nomesClubesUnicos.add(partida.getVisitante());
        }
        List<String> listaNomes = new ArrayList<>(nomesClubesUnicos);
        listaNomes.sort(String::compareTo);
        return listaNomes;
    }
    
    public Map<String, Integer> obterEstatisticasAtleta(String nomeAtleta, List<Gol> gols, List<Cartao> cartoes) {
        Map<String, Integer> estatisticas = new HashMap<>();
        
        // Inicializa o mapa com as categorias
        estatisticas.put("Gols Regulares", 0);
        estatisticas.put("Gols de Pênalti", 0);
        estatisticas.put("Gols Contra", 0);
        estatisticas.put("Cartões Amarelos", 0);
        estatisticas.put("Cartões Vermelhos", 0);
        
        // Contar gols
        for (Gol gol : gols) {
            if (gol.getAtleta().equalsIgnoreCase(nomeAtleta)) {
                switch (gol.getTipo().toLowerCase()) {
                    case "regular":
                        estatisticas.put("Gols Regulares", estatisticas.get("Gols Regulares") + 1);
                        break;
                    case "penalti":
                        estatisticas.put("Gols de Pênalti", estatisticas.get("Gols de Pênalti") + 1);
                        break;
                    case "contra":
                        estatisticas.put("Gols Contra", estatisticas.get("Gols Contra") + 1);
                        break;
                }
            }
        }
        
        // Contar cartões
        for (Cartao cartao : cartoes) {
            if (cartao.getAtleta().equalsIgnoreCase(nomeAtleta)) {
                if (cartao.getTipo().equalsIgnoreCase("amarelo")) {
                    estatisticas.put("Cartões Amarelos", estatisticas.get("Cartões Amarelos") + 1);
                } else if (cartao.getTipo().equalsIgnoreCase("vermelho")) {
                    estatisticas.put("Cartões Vermelhos", estatisticas.get("Cartões Vermelhos") + 1);
                }
            }
        }
        
        return estatisticas;
    }
    
    public Map<String, Integer> obterEstatisticasClube(String nomeClube, List<Partida> partidas) {
        Map<String, Integer> estatisticas = new HashMap<>();
        
        estatisticas.put("Vitórias", 0);
        estatisticas.put("Derrotas", 0);
        estatisticas.put("Empates", 0);
        estatisticas.put("Gols Marcados", 0);
        estatisticas.put("Gols Sofridos", 0);
        
        for (Partida partida : partidas) {
            if (partida.getMandante().equalsIgnoreCase(nomeClube)) {
                // Se o clube é o mandante
                if (partida.getVencedorEstado() != null && partida.getVencedor().equalsIgnoreCase(nomeClube)) {
                    estatisticas.put("Vitórias", estatisticas.get("Vitórias") + 1);
                } else if (partida.getVencedorEstado() != null) {
                    estatisticas.put("Derrotas", estatisticas.get("Derrotas") + 1);
                } else {
                    estatisticas.put("Empates", estatisticas.get("Empates") + 1);
                }
                estatisticas.put("Gols Marcados", estatisticas.get("Gols Marcados") + partida.getPlacarMandante());
                estatisticas.put("Gols Sofridos", estatisticas.get("Gols Sofridos") + partida.getPlacarVisitante());
            } else if (partida.getVisitante().equalsIgnoreCase(nomeClube)) {
                // Se o clube é o visitante
                if (partida.getVencedorEstado() != null && partida.getVencedor().equalsIgnoreCase(nomeClube)) {
                    estatisticas.put("Vitórias", estatisticas.get("Vitórias") + 1);
                } else if (partida.getVencedorEstado() != null) {
                    estatisticas.put("Derrotas", estatisticas.get("Derrotas") + 1);
                } else {
                    estatisticas.put("Empates", estatisticas.get("Empates") + 1);
                }
                estatisticas.put("Gols Marcados", estatisticas.get("Gols Marcados") + partida.getPlacarVisitante());
                estatisticas.put("Gols Sofridos", estatisticas.get("Gols Sofridos") + partida.getPlacarMandante());
            }
        }
        return estatisticas;
    }
}
