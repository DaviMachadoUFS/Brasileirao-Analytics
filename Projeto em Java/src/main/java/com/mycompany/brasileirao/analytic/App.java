package com.mycompany.brasileirao.analytic;

import com.mycompany.brasileirao.analytic.controller.LeitorCSV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import com.mycompany.brasileirao.analytic.model.Partida;
import com.mycompany.brasileirao.analytic.model.Gol;
import com.mycompany.brasileirao.analytic.model.Cartao;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        LeitorCSV leitor = new LeitorCSV();
        List<Partida> partidas = leitor.carregarPartidas("csv/campeonato-brasileiro-full.csv");
        List<Gol> gols = leitor.carregarGols("csv/campeonato-brasileiro-gols.csv");
        List<Cartao> cartoes = leitor.carregarCartoes("csv/campeonato-brasileiro-cartoes.csv");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/brasileirao/analytic/primary.fxml"));
        Parent root = loader.load();
        PrimaryController controller = loader.getController();
        controller.setDados(partidas, gols, cartoes);
        Scene scene = new Scene(root, 780, 628);
        stage.setScene(scene);
        stage.setTitle("Brasileirão Analytics");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}