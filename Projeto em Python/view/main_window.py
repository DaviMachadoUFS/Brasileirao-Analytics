import sys
import os
from PyQt5 import uic
from PyQt5.QtWidgets import QMainWindow, QPushButton, QComboBox, QLabel, QGraphicsScene, QGraphicsView, QMessageBox, QCheckBox
from PyQt5.QtGui import QPainter, QBrush, QColor, QFont, QPen, QPainterPath
from PyQt5.QtCore import Qt, QRectF
import math

from controller.processador_dados import ProcessadorDados

class MainWindow(QMainWindow):
    def __init__(self, processador):
        super().__init__()
        self.processador = processador
        self.setWindowTitle("Estatísticas do Brasileirão")

        ui_path = os.path.join(os.path.dirname(__file__), 'Brasileirao-Analytics.ui')
        uic.loadUi(ui_path, self)

        self.btn_regiao = self.findChild(QPushButton, 'btn_regiao')
        self.btn_atleta = self.findChild(QPushButton, 'btn_atleta')
        self.btn_clube = self.findChild(QPushButton, 'btn_clube')
        self.btn_comp_atleta = self.findChild(QPushButton, 'btn_comp_atleta')
        self.btn_comp_time = self.findChild(QPushButton, 'btn_comp_time')

        self.combo_atleta1 = self.findChild(QComboBox, 'combo_atleta1')
        self.combo_atleta2 = self.findChild(QComboBox, 'combo_atleta2')
        self.combo_time1 = self.findChild(QComboBox, 'combo_time1')
        self.combo_time2 = self.findChild(QComboBox, 'combo_time2')

        self.checkBarra = self.findChild(QCheckBox, 'checkBarra')
        self.checkPizza = self.findChild(QCheckBox, 'checkPizza')

        self.grafico_view = self.findChild(QGraphicsView, 'grafico_view')
        self.titulo_grafico = self.findChild(QLabel, 'titulo_grafico')
        
        self.grafico_scene = QGraphicsScene(self)
        self.grafico_view.setScene(self.grafico_scene)
        self.grafico_view.setRenderHint(QPainter.Antialiasing)

        nomes_atletas = self.processador.obter_nomes_atletas()
        self.combo_atleta1.addItems(nomes_atletas)
        self.combo_atleta2.addItems(nomes_atletas)
        nomes_times = self.processador.obter_nomes_clubes()
        self.combo_time1.addItems(nomes_times)
        self.combo_time2.addItems(nomes_times)

        self.btn_regiao.clicked.connect(self.gerar_grafico_regiao)
        self.btn_atleta.clicked.connect(self.gerar_grafico_atleta)
        self.btn_clube.clicked.connect(self.gerar_grafico_clube)
        self.btn_comp_atleta.clicked.connect(self.comparar_atletas)
        self.btn_comp_time.clicked.connect(self.comparar_times)

    def gerar_grafico_regiao(self):
        dados = self.processador.contar_vitorias_por_regiao()
        self.plotar_graficos("Vitórias por Região", dados)

    def gerar_grafico_atleta(self):
        atleta = self.combo_atleta1.currentText()
        if not atleta:
            self.mostrar_alerta("Aviso", "Por favor, selecione um atleta.")
            return

        dados = self.processador.obter_estatisticas_atleta(atleta)
        self.plotar_graficos(f"Estatísticas de {atleta}", dados)

    def gerar_grafico_clube(self):
        clube = self.combo_time1.currentText()
        if not clube:
            self.mostrar_alerta("Aviso", "Por favor, selecione um time.")
            return

        dados = self.processador.obter_estatisticas_clube(clube)
        self.plotar_graficos(f"Estatísticas do {clube}", dados)

    def comparar_atletas(self):
        atleta1 = self.combo_atleta1.currentText()
        atleta2 = self.combo_atleta2.currentText()

        if not atleta1 or not atleta2:
            self.mostrar_alerta("Seleção Incompleta", "Por favor, selecione dois atletas para comparar.")
            return
            
        if not self.checkBarra.isChecked():
            self.mostrar_alerta("Aviso", "A comparação é mais eficaz com o 'Gráfico de Barras'. Por favor, selecione-o.")
            return

        dados1 = self.processador.obter_estatisticas_atleta(atleta1)
        dados2 = self.processador.obter_estatisticas_atleta(atleta2)
        
        self.plotar_grafico_comparativo(
            f"Comparação entre {atleta1} e {atleta2}",
            dados1,
            dados2,
            atleta1,
            atleta2
        )

    def comparar_times(self):
        time1 = self.combo_time1.currentText()
        time2 = self.combo_time2.currentText()

        if not time1 or not time2:
            self.mostrar_alerta("Seleção Incompleta", "Por favor, selecione dois times para comparar.")
            return
            
        if not self.checkBarra.isChecked():
            self.mostrar_alerta("Aviso", "A comparação é mais eficaz com o 'Gráfico de Barras'. Por favor, selecione-o.")
            return
        
        dados1 = self.processador.obter_estatisticas_clube(time1)
        dados2 = self.processador.obter_estatisticas_clube(time2)
        
        self.plotar_grafico_comparativo(
            f"Comparação entre {time1} e {time2}",
            dados1,
            dados2,
            time1,
            time2
        )

    def plotar_graficos(self, titulo, dados):
        self.grafico_scene.clear()
        self.titulo_grafico.setText(titulo)
        
        if self.checkBarra.isChecked() and not self.checkPizza.isChecked():
            self.plotar_grafico_barras_simples(titulo, dados)
        elif self.checkPizza.isChecked() and not self.checkBarra.isChecked():
            self.plotar_grafico_pizza(titulo, dados)
        elif self.checkBarra.isChecked() and self.checkPizza.isChecked():
            self.plotar_grafico_barras_simples(titulo, dados)
        else:
            self.mostrar_alerta("Aviso", "Por favor, selecione pelo menos um tipo de gráfico para continuar.")

    def plotar_grafico_barras_simples(self, titulo, dados):
        self.grafico_scene.clear()
        self.titulo_grafico.setText(titulo)
        
        width = self.grafico_view.width()
        height = self.grafico_view.height()
        padding = 80
        labels = dados.keys()
        valores = dados.values()

        max_valor = max(valores) if valores else 1
        num_barras = len(labels)
        bar_total_width = width - 2 * padding
        if num_barras > 1:
            bar_spacing = 20
        else:
            bar_spacing = 0
            
        bar_width = (bar_total_width - (num_barras - 1) * bar_spacing) / num_barras
        
        for i, (label, valor) in enumerate(zip(labels, valores)):
            x0 = padding + i * (bar_width + bar_spacing)
            y0 = height - padding
            
            bar_height = (valor / max_valor) * (height - 2 * padding)
            y1 = y0 - bar_height
            
            color = QColor(int(hash(label) * 123) % 255, int(hash(label) * 456) % 255, int(hash(label) * 789) % 255)
            
            self.grafico_scene.addRect(x0, y1, bar_width, bar_height,
            QPen(Qt.black), QBrush(color))
            
            label_item = self.grafico_scene.addText(label)
            label_item.setPos(x0, y0 + 10)
            
            valor_item = self.grafico_scene.addText(str(valor))
            valor_item.setPos(x0, y1 - 20)

    def plotar_grafico_pizza(self, titulo, dados):
        self.grafico_scene.clear()
        self.titulo_grafico.setText(titulo)

        width = self.grafico_view.width()
        height = self.grafico_view.height()
        
        cx = width / 2
        cy = height / 2
        pizza_radius = min(width, height) / 3
        
        total = sum(dados.values())
        start_angle = 90 * 16

        legenda_x = 20
        legenda_y = 50
        legenda_item_altura = 25
        legenda_cor_largura = 20
        
        for i, (label, valor) in enumerate(dados.items()):
            if total == 0:
                continue

            angle = (valor / total) * 360 * 16
            color = QColor(int(hash(label) * 123) % 255, int(hash(label) * 456) % 255, int(hash(label) * 789) % 255)

            path = QPainterPath()
            path.moveTo(cx, cy)
            path.arcTo(QRectF(cx - pizza_radius, cy - pizza_radius, pizza_radius * 2, pizza_radius * 2), start_angle / 16, angle / 16)
            path.closeSubpath()
            self.grafico_scene.addPath(path, QPen(Qt.black), QBrush(color))
            
            legenda_cor_y = legenda_y + i * legenda_item_altura
            self.grafico_scene.addRect(legenda_x, legenda_cor_y, legenda_cor_largura, 10, QPen(Qt.black), QBrush(color))
            
            legenda_texto = f"{label} ({valor})"
            self.grafico_scene.addText(legenda_texto).setPos(legenda_x + legenda_cor_largura + 5, legenda_cor_y - 5)
            
            start_angle += angle
    
    def plotar_grafico_comparativo(self, titulo, dados1, dados2, nome1, nome2):
        self.grafico_scene.clear()
        self.titulo_grafico.setText(titulo)
        
        labels = list(dados1.keys())
        width = self.grafico_view.width()
        height = self.grafico_view.height()
        padding = 80
        
        valores_total = list(dados1.values()) + list(dados2.values())
        max_valor = max(valores_total) if valores_total else 1
        num_labels = len(labels)
        
        bar_group_width = (width - 2 * padding) / num_labels
        bar_width = (bar_group_width - 20) / 2
        
        color1 = QColor(100, 150, 255)
        color2 = QColor(255, 100, 100)

        for i, label in enumerate(labels):
            valor1 = dados1.get(label, 0)
            valor2 = dados2.get(label, 0)

            x_base = padding + i * bar_group_width
            y_base = height - padding

            bar_height1 = (valor1 / max_valor) * (height - 2 * padding)
            self.grafico_scene.addRect(x_base, y_base - bar_height1, bar_width, bar_height1,
            QPen(Qt.black), QBrush(color1))
            self.grafico_scene.addText(str(valor1)).setPos(x_base, y_base - bar_height1 - 20)
            
            x_bar2 = x_base + bar_width + 5
            bar_height2 = (valor2 / max_valor) * (height - 2 * padding)
            self.grafico_scene.addRect(x_bar2, y_base - bar_height2, bar_width, bar_height2,
            QPen(Qt.black), QBrush(color2))
            self.grafico_scene.addText(str(valor2)).setPos(x_bar2, y_base - bar_height2 - 20)
            
            label_item = self.grafico_scene.addText(label)
            label_item.setPos(x_base + (bar_group_width - label_item.boundingRect().width()) / 2, y_base + 10)
        
        legenda_y = 50
        legenda_x_base = 20
        self.grafico_scene.addRect(legenda_x_base, legenda_y, 10, 10, QPen(Qt.black), QBrush(color1))
        self.grafico_scene.addText(nome1).setPos(legenda_x_base + 15, legenda_y - 8)
        self.grafico_scene.addRect(legenda_x_base, legenda_y + 20, 10, 10, QPen(Qt.black), QBrush(color2))
        self.grafico_scene.addText(nome2).setPos(legenda_x_base + 15, legenda_y + 12)

    def mostrar_alerta(self, titulo, mensagem):
        alerta = QMessageBox()
        alerta.setWindowTitle(titulo)
        alerta.setText(mensagem)
        alerta.setIcon(QMessageBox.Warning)
        alerta.exec_()