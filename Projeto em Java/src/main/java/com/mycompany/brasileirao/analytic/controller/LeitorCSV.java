/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brasileirao.analytic.controller;
import com.mycompany.brasileirao.analytic.model.Partida;
import com.mycompany.brasileirao.analytic.model.Gol;
import com.mycompany.brasileirao.analytic.model.Cartao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davim
 */
public class LeitorCSV {

    public List<Partida> carregarPartidas(String caminho) {
        List<Partida> partidas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha = br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.trim().split(",");
                if (colunas.length < 6) {
                    continue;
                }
                
                String mandante = colunas[0].trim().replace("\"", "");
                String visitante = colunas[1].trim().replace("\"", "");
                String vencedor = colunas[2].trim().replace("\"", "");
                String vencedorEstado = colunas[5].trim().replace("\"", "");
                
                int placarMandante;
                int placarVisitante;
                try {
                    placarMandante = Integer.parseInt(colunas[3].trim().replace("\"", ""));
                    placarVisitante = Integer.parseInt(colunas[4].trim().replace("\"", ""));
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter placar para número na linha: " + linha);
                    continue;
                }

                if (vencedorEstado.isEmpty()) {
                    vencedorEstado = null;
                }

                partidas.add(new Partida(mandante, visitante, vencedor, placarMandante, placarVisitante, vencedorEstado));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + caminho);
        } catch (IOException e) {
            System.err.println("Erro de leitura do arquivo: " + e.getMessage());
        }
        return partidas;
    }

    public List<Gol> carregarGols(String caminho) {
        List<Gol> gols = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.trim().split(",");
                if (colunas.length < 3) {
                    continue;
                }
                String time = colunas[0].trim().replace("\"", "");
                String atleta = colunas[1].trim().replace("\"", "");
                String tipo = colunas[2].trim().replace("\"", "");
                
                gols.add(new Gol(time, atleta, tipo));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + caminho);
        } catch (IOException e) {
            System.err.println("Erro de leitura do arquivo: " + e.getMessage());
        }
        return gols;
    }

    public List<Cartao> carregarCartoes(String caminho) {
        List<Cartao> cartoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.trim().split(",");
                if (colunas.length < 3) {
                    continue;
                }
                String time = colunas[0].trim().replace("\"", "");
                String tipo = colunas[1].trim().replace("\"", "");
                String atleta = colunas[2].trim().replace("\"", "");
                
                cartoes.add(new Cartao(time, tipo, atleta));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + caminho);
        } catch (IOException e) {
            System.err.println("Erro de leitura do arquivo: " + e.getMessage());
        }
        return cartoes;
    }
}
