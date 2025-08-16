import controller.LeitorCSV;
import model.Partida;
import model.Cartao;
import model.Gol;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        String fullCsvCaminho = "campeonato-brasileiro-full.csv";
        String golsCsvCaminho = "campeonato-brasileiro-gols.csv";
        String cartoesCsvCaminho = "campeonato-brasileiro-cartoes.csv";

        LeitorCSV leitor = new LeitorCSV();

        System.out.println("--- Carregarndo Partidas ---");
        List<Partida> partidas = leitor.carregarPartidas(fullCsvCaminho);
        partidas.forEach(System.out::println);

        System.out.println("--- Carregando Gols ---");
        List<Gol> gols = leitor.carregarGols(golsCsvCaminho);
        gols.forEach(System.out::println);

        System.out.println("--- Carregando Cartoes ---");
        List<Cartao> cartoes = leitor.carregarCartoes(cartoesCsvCaminho);
        cartoes.forEach(System.out::println);
    }
}
