/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.text.Collator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Partida;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import model.Gol;
import model.Cartao;

/**
 *
 * @author davim
 */
public class ProcessadorDados {
    
    private static final Map<String ,String> estadoParaRegiao = new HashMap<>();
    
    static {
        estadoParaRegiao.put("SP", "Sudeste");
        estadoParaRegiao.put("MG", "Sudeste");
        estadoParaRegiao.put("RJ", "Sudeste");
        estadoParaRegiao.put("ES", "Sudeste");
        estadoParaRegiao.put("PR", "Sul");
        estadoParaRegiao.put("SC", "Sul");
        estadoParaRegiao.put("RS", "Sul");
        estadoParaRegiao.put("MS", "Centro-Oeste");
        estadoParaRegiao.put("MT", "Centro-Oeste");
        estadoParaRegiao.put("GO", "Centro-Oeste");
        estadoParaRegiao.put("DF", "Centro-Oeste");
        estadoParaRegiao.put("RR", "Norte");
        estadoParaRegiao.put("RO", "Norte");
        estadoParaRegiao.put("AC", "Norte");
        estadoParaRegiao.put("AM", "Norte");
        estadoParaRegiao.put("PA", "Norte");
        estadoParaRegiao.put("TO", "Norte");
        estadoParaRegiao.put("AP", "Norte");
        estadoParaRegiao.put("SE", "Nordeste");
        estadoParaRegiao.put("BA", "Nordeste");
        estadoParaRegiao.put("AL", "Nordeste");
        estadoParaRegiao.put("CE", "Nordeste");
        estadoParaRegiao.put("RN", "Nordeste");
        estadoParaRegiao.put("PB", "Nordeste");
        estadoParaRegiao.put("PE", "Nordeste");
        estadoParaRegiao.put("MA", "Nordeste");
        estadoParaRegiao.put("PI", "Nordeste");
    }
    
    public Map<String, Integer> contarVitoriasPorRegiao(List<Partida> partidas){
        Map<String, Integer> vitoriasPorRegiao = new HashMap<>();
        
        estadoParaRegiao.values().forEach(regiao -> vitoriasPorRegiao.put(regiao, 0));
        for (Partida partida : partidas){
            String estadoVencedor = partida.getVencedorEstado();
        
            if (estadoVencedor != null){
                String regiao = estadoParaRegiao.get(estadoVencedor);
                if (regiao != null){
                    vitoriasPorRegiao.put(regiao, vitoriasPorRegiao.get(regiao) + 1);
                }
            }
        }
        return vitoriasPorRegiao;
    }
    
    public List<String> obterNomesAtletas(List<Gol> gols, List<Cartao> cartoes){
        Set<String> nomesAtletasUnicos = new HashSet<>();
        
        for (Gol gol : gols){
            nomesAtletasUnicos.add(gol.getAtleta());
        }
        
        for (Cartao cartao : cartoes){
            nomesAtletasUnicos.add(cartao.getAtleta());
        }
        List<String> atletasOrdenados = new ArrayList<>(nomesAtletasUnicos);
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        collator.setStrength(Collator.PRIMARY);
        Collections.sort(atletasOrdenados, collator);
        
        return atletasOrdenados;
    }
    
    public Map<String, Integer> processarEstatisticasAtleta(String nomeAtleta, List<Gol> gols, List<Cartao> cartoes) {
        Map<String, Integer> estatisticas = new HashMap<>();
        //
        estatisticas.put("Gols Regulares", 0);
        estatisticas.put("Gols de Pênalti", 0);
        estatisticas.put("Gols Contra", 0);
        estatisticas.put("Cartões Amarelos", 0);
        estatisticas.put("Cartões Vermelhos", 0);
    
        for (Gol gol : gols) {
            if (gol.getAtleta().equalsIgnoreCase(nomeAtleta)) {
                String tipoGol = gol.getTipo();
                if (tipoGol.equalsIgnoreCase("regular")) {
                    estatisticas.put("Gols Regulares", estatisticas.get("Gols Regulares") + 1);
                } else if (tipoGol.equalsIgnoreCase("penalti")) {
                    estatisticas.put("Gols de Pênalti", estatisticas.get("Gols de Pênalti") + 1);
                } else if (tipoGol.equalsIgnoreCase("contra")) {
                    estatisticas.put("Gols Contra", estatisticas.get("Gols Contra") + 1);
                }
            }
        }
    
        for (Cartao cartao : cartoes) {
            if (cartao.getAtleta().equalsIgnoreCase(nomeAtleta)) {
                String tipoCartao = cartao.getTipo();
                if (tipoCartao.equalsIgnoreCase("amarelo")) {
                    estatisticas.put("Cartões Amarelos", estatisticas.get("Cartões Amarelos") + 1);
                } else if (tipoCartao.equalsIgnoreCase("vermelho")) {
                    estatisticas.put("Cartões Vermelhos", estatisticas.get("Cartões Vermelhos") + 1);
                }
            }
        }
    
        return estatisticas;
    }
    
    public List<String> obterNomesClubes(List<Partida> partidas) {
        Set<String> nomesClubesUnicos = new HashSet<>();

        for (Partida partida : partidas) {
            nomesClubesUnicos.add(partida.getMandante());
            nomesClubesUnicos.add(partida.getVisitante());
        }

        // Retorna uma lista ordenada para o JComboBox
        List<String> clubesOrdenados = new ArrayList<>(nomesClubesUnicos);
        Collections.sort(clubesOrdenados);
    
        return clubesOrdenados;
    }
    
    public Map<String, Integer> processarEstatisticasClube(String nomeClube, List<Partida> partidas, List<Gol> gols) {
        Map<String, Integer> estatisticas = new HashMap<>();
    
        // Inicializa o mapa com as estatísticas a zero
        estatisticas.put("Vitórias", 0);
        estatisticas.put("Derrotas", 0);
        estatisticas.put("Gols Regulares", 0);
        estatisticas.put("Gols de Pênalti", 0);
        estatisticas.put("Gols Contra", 0);
    
        // Contagem de vitórias, derrotas e empates
        for (Partida partida : partidas) {
            // Verifica se o clube é o mandante ou visitante
            if (partida.getMandante().equalsIgnoreCase(nomeClube) || partida.getVisitante().equalsIgnoreCase(nomeClube)) {
                // Se houver um vencedor
                if (partida.getVencedor() != null && !partida.getVencedor().isEmpty()) {
                    if (partida.getVencedor().equalsIgnoreCase(nomeClube)) {
                        estatisticas.put("Vitórias", estatisticas.get("Vitórias") + 1);
                    } else {
                        estatisticas.put("Derrotas", estatisticas.get("Derrotas") + 1);
                    }
                }
            }
        }
    
        // Contagem de gols por tipo
        for (Gol gol : gols) {
            if (gol.getClube().equalsIgnoreCase(nomeClube)) {
                String tipoGol = gol.getTipo();
                if (tipoGol.equalsIgnoreCase("regular")) {
                    estatisticas.put("Gols Regulares", estatisticas.get("Gols Regulares") + 1);
                } else if (tipoGol.equalsIgnoreCase("penalti")) {
                    estatisticas.put("Gols de Pênalti", estatisticas.get("Gols de Pênalti") + 1);
                } else if (tipoGol.equalsIgnoreCase("contra")) {
                    estatisticas.put("Gols Contra", estatisticas.get("Gols Contra") + 1);
                }
            }
        }
    
        return estatisticas;
    }
}
