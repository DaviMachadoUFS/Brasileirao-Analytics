import sys
from PyQt5.QtWidgets import QApplication
from controller.leitor_csv import LeitorCSV
from controller.processador_dados import ProcessadorDados
from view.main_window import MainWindow

if __name__ == "__main__":
    app = QApplication(sys.argv)

    leitor = LeitorCSV()
    partidas = leitor.carregar_partidas("csv/campeonato-brasileiro-full.csv")
    gols = leitor.carregar_gols("csv/campeonato-brasileiro-gols.csv")
    cartoes = leitor.carregar_cartoes("csv/campeonato-brasileiro-cartoes.csv")
    
    processador = ProcessadorDados(partidas, gols, cartoes)
    
    main_window = MainWindow(processador)
    main_window.show()
    
    sys.exit(app.exec_())