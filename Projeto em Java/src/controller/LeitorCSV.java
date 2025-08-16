package controller;

import model.Partida;
import model.Cartao;
import model.Gol;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorCSV {
    
    public List<Partida> carregarPartidas(String caminho) {
        List<Partida> partidas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha = br.readLine(); //pula o cabecalho
            while ((linha = br.readLine()) != null){
                String[] colunas = linha.split(",");
                if (colunas.length < 6){ //tratamento para linhas quebradas
                    continue;
                }
                try {
                    String mandante = colunas[0].trim().replace("\"", "");
                    String visitante = colunas[1].trim().replace("\"", "");
                    String vencedor = colunas[2].trim().replace("\"", "");
                    int placarMandante = Integer.parseInt(colunas[3].trim().replace("\"", ""));
                    int placarVisitante = Integer.parseInt(colunas[4].trim().replace("\"", ""));
                    String vencedorEstado = colunas[5].trim().replace("\"", "");

                    if (vencedorEstado.isEmpty()){ //tratamento para caso de empate
                        vencedorEstado = null;
                    }

                    partidas.add(new Partida(mandante, visitante, vencedor, placarMandante, placarVisitante, vencedorEstado));
                }catch (NumberFormatException e){
                    System.err.println("Erro ao converter placar para número na linha: " + linha);
                }
            }
        }catch (FileNotFoundException e){
            System.err.println("Arquivo 'campeonato-brasileiro-full.csv' nao encontrado no caminho: " + caminho);
        }catch (IOException e){
            System.err.println("Erro de leitura do arquivo: " + e.getMessage());
        }
        return partidas;
    }

    public List<Gol> carregarGols(String caminho){
        List<Gol> gols = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha = br.readLine(); //pula o cabecalho
            while ((linha = br.readLine()) != null){
                String[] colunas = linha.split(",");
                if (colunas.length < 3){ //tratamento para caso de linha quebrada
                    continue;
                }

                String clube = colunas[0].trim().replace("\"", "");
                String atleta = colunas[1].trim().replace("\"", "");
                String tipo = colunas[2].trim().replace("\"", "");

                gols.add(new Gol(clube, atleta, tipo));
            }
        }catch (FileNotFoundException e){
            System.err.println("Arquivo 'campeonato-brasileiro-gols.csv' nao encontrado no caminho: " + caminho);
        }catch (IOException e){
            System.err.println("Erro de leitura do arquivo: " + e.getMessage());
        }
        return gols;
    }

    public List<Cartao> carregarCartoes(String caminho){
        List<Cartao> cartoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha = br.readLine(); //pula o cabecalho
            while ((linha = br.readLine()) != null){
                String[] colunas = linha.split(",");
                if (colunas.length < 3){ //tratamento para caso de linha quebrada
                    continue;
                }

                String clube = colunas[0].trim().replace("\"", "");
                String tipo = colunas[1].trim().replace("\"", "");
                String atleta = colunas[2].trim().replace("\"", "");

                cartoes.add(new Cartao(clube, tipo, atleta));
            }
        }catch (FileNotFoundException e){
            System.err.println("Arquivo 'campeonato-brasileiro-cartoes.csv' nao encontrado no caminho: " + caminho);
        }catch (IOException e){
            System.err.println("Erro de leitura no arquivo: " + e.getMessage());
        }
        return cartoes;
    }
}