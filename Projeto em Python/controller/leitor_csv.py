import csv
from model.partida import Partida
from model.gol import Gol
from model.cartao import Cartao

class LeitorCSV:
    def carregar_partidas(self, caminho):
        partidas = []
        try:
            with open(caminho, 'r', encoding='utf-8') as arquivo:
                leitor = csv.reader(arquivo)
                next(leitor) #pulando o cabeçalho
                for linha in leitor:
                    if len(linha) < 6: #pulando casos de linhas com dados ausentes
                        continue
                    try:
                        partida = Partida(
                            mandante=linha[0].strip().replace('"', ''),
                            visitante=linha[1].strip().replace('"', ''),
                            vencedor=linha[2].strip().replace('"', ''),
                            placar_mandante=int(linha[3].strip().replace('"', '')),
                            placar_visitante=int(linha[4].strip().replace('"', '')),
                            vencedor_estado=linha[5].strip().replace('"', '') if linha[5].strip().replace('"', '') else None
                        )
                        partidas.append(partida)
                    except (ValueError, IndexError) as e:
                        print(f"Erro ao processar a linha: {linha}. Erro: {e}")
        except FileNotFoundError:
            print(f"Arquivo não encontrado: {caminho}")
        return partidas
    
    def carregar_gols(self, caminho):
        gols = []
        try:
            with open(caminho, 'r', encoding='utf-8') as arquivo:
                leitor = csv.reader(arquivo)
                next(leitor) #pulando o cabeçalho
                for linha in leitor:
                    if len(linha) < 3: #pulando casos de linhas com dados ausentes
                        continue
                    gol = Gol(
                        clube=linha[0].strip().replace('"', ''),
                        atleta=linha[1].strip().replace('"', ''),
                        tipo=linha[2].strip().replace('"', '')
                    )
                    gols.append(gol)
        except FileNotFoundError:
            print(f"Arquivo não encontrado: {caminho}")
        return gols
    
    def carregar_cartoes(self, caminho):
        cartoes = []
        try:
            with open(caminho, 'r', encoding='utf-8') as arquivo:
                leitor = csv.reader(arquivo)
                next(leitor) #pulando o cabeçalho
                for linha in leitor:
                    if len(linha) < 3: #pulando casos de linhas com dados ausentes
                        continue
                    cartao = Cartao(
                        clube=linha[0].strip().replace('"', ''),
                        tipo=linha[1].strip().replace('"', ''),
                        atleta=linha[2].strip().replace('"', '')
                    )
                    cartoes.append(cartao)
        except FileNotFoundError:
            print(f"Arquivo não encontrado: {caminho}")
        return cartoes
