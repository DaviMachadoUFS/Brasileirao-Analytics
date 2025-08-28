/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.brasileirao.analytic;

import com.mycompany.brasileirao.analytic.controller.ProcessadorDados;
import com.mycompany.brasileirao.analytic.model.Cartao;
import com.mycompany.brasileirao.analytic.model.Gol;
import com.mycompany.brasileirao.analytic.model.Partida;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * FXML Controller class
 *
 * @author davim
 */
public class PrimaryController implements Initializable {


    @FXML private BarChart<String, Number> graficoBarras;
    @FXML private PieChart graficoPizza;
    @FXML private ComboBox<String> comboAtleta1;
    @FXML private ComboBox<String> comboAtleta2;
    @FXML private Button grafRegiao;
    @FXML private Button grafAtleta;
    @FXML private Button compararAtletas;
    @FXML private ComboBox<String> comboTime1;
    @FXML private ComboBox<String> comboTime2;
    @FXML private Button grafTime;
    @FXML private Button compararTimes;
    @FXML private CheckBox selBarra;
    @FXML private CheckBox selPizza;
    
    private List<Partida> partidas;
    private List<Gol> gols;
    private List<Cartao> cartoes;
    private ProcessadorDados processador;
    
    public void setDados(List<Partida> partidas, List<Gol> gols, List<Cartao> cartoes){
        this.partidas = partidas;
        this.gols = gols;
        this.cartoes = cartoes;
        this.processador = new ProcessadorDados();
        
        List<String> nomesAtletas = processador.obterNomesAtletas(gols, cartoes);
        comboAtleta1.getItems().addAll(nomesAtletas);
        comboAtleta2.getItems().addAll(nomesAtletas);

        List<String> nomesClubes = processador.obterNomesClubes(partidas);
        comboTime1.getItems().addAll(nomesClubes);
        comboTime2.getItems().addAll(nomesClubes);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        graficoBarras.setVisible(false);
        graficoPizza.setVisible(false);
    }    
    
    @FXML
    private void selectAtleta1(ActionEvent event) {}
    
    @FXML
    private void selectAtleta2(ActionEvent event) {}

    @FXML
    private void gerarRegiao(ActionEvent event) {
        if (!selBarra.isSelected() && !selPizza.isSelected()) {
            showAlert("Nenhum tipo de gráfico selecionado", "Por favor, selecione um ou ambos os tipos de gráfico.");
            return;
        }

        Map<String, Integer> vitoriasPorRegiao = processador.contarVitoriasPorRegiao(partidas);
        
        if (selBarra.isSelected()) {
            gerarGraficoBarra(vitoriasPorRegiao, "Vitórias por Região", "Total de Vitórias");
            graficoBarras.setVisible(true);
        } else {
            graficoBarras.setVisible(false);
        }

        if (selPizza.isSelected()) {
            gerarGraficoPizza(vitoriasPorRegiao, "Vitórias por Região");
            graficoPizza.setVisible(true);
        } else {
            graficoPizza.setVisible(false);
        }
    }

    @FXML
    private void gerarAtleta(ActionEvent event) {
        String atletaSelecionado = comboAtleta1.getValue();
        
        if (atletaSelecionado == null || atletaSelecionado.isEmpty()){
            showAlert("Nenhum atleta selecionado", "Por favor, selecione um atleta na lista.");
            return;
        }

        if (!selBarra.isSelected() && !selPizza.isSelected()) {
            showAlert("Nenhum tipo de gráfico selecionado", "Por favor, selecione um ou ambos os tipos de gráfico.");
            return;
        }

        Map<String, Integer> estatisticasAtleta = processador.obterEstatisticasAtleta(atletaSelecionado, gols, cartoes);

        if (selBarra.isSelected()) {
            gerarGraficoBarra(estatisticasAtleta, "Estatísticas de " + atletaSelecionado, atletaSelecionado);
            graficoBarras.setVisible(true);
        } else {
            graficoBarras.setVisible(false);
        }

        if (selPizza.isSelected()) {
            gerarGraficoPizza(estatisticasAtleta, "Estatísticas de " + atletaSelecionado);
            graficoPizza.setVisible(true);
        } else {
            graficoPizza.setVisible(false);
        }
    }

    @FXML
    private void selectTime1(ActionEvent event) {}
    
    @FXML
    private void selectTime2(ActionEvent event) {}
    
    @FXML
    private void gerarTime(ActionEvent event) {
        String clubeSelecionado = comboTime1.getValue();
        
        if (clubeSelecionado == null || clubeSelecionado.isEmpty()) {
            showAlert("Nenhum clube selecionado", "Por favor, selecione um clube na lista.");
            return;
        }

        if (!selBarra.isSelected() && !selPizza.isSelected()) {
            showAlert("Nenhum tipo de gráfico selecionado", "Por favor, selecione um ou ambos os tipos de gráfico.");
            return;
        }

        Map<String, Integer> estatisticasClube = processador.obterEstatisticasClube(clubeSelecionado, partidas);

        if (selBarra.isSelected()) {
            gerarGraficoBarra(estatisticasClube, "Estatísticas de " + clubeSelecionado, clubeSelecionado);
            graficoBarras.setVisible(true);
        } else {
            graficoBarras.setVisible(false);
        }
        
        if (selPizza.isSelected()) {
            gerarGraficoPizza(estatisticasClube, "Estatísticas de " + clubeSelecionado);
            graficoPizza.setVisible(true);
        } else {
            graficoPizza.setVisible(false);
        }
    }

    @FXML
    private void compararAtletas(ActionEvent event) {
        String atleta1 = comboAtleta1.getValue();
        String atleta2 = comboAtleta2.getValue();

        if (atleta1 == null || atleta1.isEmpty() || atleta2 == null || atleta2.isEmpty()) {
            showAlert("Seleção Incompleta", "Por favor, selecione dois atletas para comparar.");
            return;
        }

        if (!selBarra.isSelected()) {
            showAlert("Tipo de Gráfico Não Selecionado", "A comparação entre atletas é mais eficaz em um Gráfico de Barras. Por favor, selecione o Gráfico de Barras.");
            return;
        }
        
        graficoBarras.getData().clear();
        graficoBarras.setTitle("Comparação entre " + atleta1 + " e " + atleta2);

        Map<String, Integer> estatisticas1 = processador.obterEstatisticasAtleta(atleta1, gols, cartoes);
        Map<String, Integer> estatisticas2 = processador.obterEstatisticasAtleta(atleta2, gols, cartoes);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName(atleta1);
        for (Map.Entry<String, Integer> entry : estatisticas1.entrySet()) {
            series1.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName(atleta2);
        for (Map.Entry<String, Integer> entry : estatisticas2.entrySet()) {
            series2.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        graficoBarras.getData().addAll(series1, series2);
        graficoBarras.setVisible(true);
        graficoPizza.setVisible(false);
    }
    
    @FXML
    private void compararTimes(ActionEvent event) {
        String time1 = comboTime1.getValue();
        String time2 = comboTime2.getValue();

        if (time1 == null || time1.isEmpty() || time2 == null || time2.isEmpty()) {
            showAlert("Seleção Incompleta", "Por favor, selecione dois times para comparar.");
            return;
        }

        if (!selBarra.isSelected()) {
            showAlert("Tipo de Gráfico Não Selecionado", "A comparação entre times é mais eficaz em um Gráfico de Barras. Por favor, selecione o Gráfico de Barras.");
            return;
        }

        graficoBarras.getData().clear();
        graficoBarras.setTitle("Comparação entre " + time1 + " e " + time2);

        Map<String, Integer> estatisticas1 = processador.obterEstatisticasClube(time1, partidas);
        Map<String, Integer> estatisticas2 = processador.obterEstatisticasClube(time2, partidas);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName(time1);
        for (Map.Entry<String, Integer> entry : estatisticas1.entrySet()) {
            series1.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName(time2);
        for (Map.Entry<String, Integer> entry : estatisticas2.entrySet()) {
            series2.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        graficoBarras.getData().addAll(series1, series2);
        graficoBarras.setVisible(true);
        graficoPizza.setVisible(false);
    }
    
    private void gerarGraficoBarra(Map<String, Integer> dados, String titulo, String seriesNome) {
        graficoBarras.getData().clear();
        graficoBarras.setTitle(titulo);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesNome);
        
        for (Map.Entry<String, Integer> entry : dados.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        
        graficoBarras.getData().add(series);
    }
    
    private void gerarGraficoPizza(Map<String, Integer> dados, String titulo) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
        for (Map.Entry<String, Integer> entry : dados.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        
        graficoPizza.setData(pieChartData);
        graficoPizza.setTitle(titulo);
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
    
