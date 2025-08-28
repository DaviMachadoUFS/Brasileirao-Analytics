import locale
from collections import defaultdict

class ProcessadorDados:
    def __init__(self, partidas, gols, cartoes):
        self.partidas = partidas
        self.gols = gols
        self.cartoes = cartoes
        self.estado_para_regiao = {
            "SP": "Sudeste", "RJ": "Sudeste", "MG": "Sudeste", "ES": "Sudeste",
            "RS": "Sul", "PR": "Sul", "SC": "Sul",
            "BA": "Nordeste", "PE": "Nordeste", "CE": "Nordeste", "RN": "Nordeste",
            "AL": "Nordeste", "SE": "Nordeste", "MA": "Nordeste", "PB": "Nordeste",
            "GO": "Centro-Oeste", "MT": "Centro-Oeste", "MS": "Centro-Oeste", "DF": "Centro-Oeste",
            "PA": "Norte", "AM": "Norte", "AC": "Norte", "AP": "Norte", "TO": "Norte",
            "RO": "Norte", "RR": "Norte"
        }

    def contar_vitorias_por_regiao(self):
        vitorias_por_regiao = defaultdict(int)
        for partida in self.partidas:
            if partida.vencedor_estado:
                regiao = self.estado_para_regiao.get(partida.vencedor_estado)
                if regiao:
                    vitorias_por_regiao[regiao] += 1
        return dict(vitorias_por_regiao)

    def obter_nomes_atletas(self):
        nomes = set()
        for gol in self.gols:
            nomes.add(gol.atleta)
        for cartao in self.cartoes:
            nomes.add(cartao.atleta)
        
        nomes_ordenados = list(nomes)
        
        try:
            locale.setlocale(locale.LC_ALL, 'pt_BR.UTF-8')
        except locale.Error:
            locale.setlocale(locale.LC_ALL, 'C.UTF-8')
        
        nomes_ordenados.sort(key=locale.strxfrm)
        
        return nomes_ordenados

    def obter_nomes_clubes(self):
        nomes = set()
        for partida in self.partidas:
            nomes.add(partida.mandante)
            nomes.add(partida.visitante)
        
        nomes_ordenados = list(nomes)
        
        try:
            locale.setlocale(locale.LC_ALL, 'pt_BR.UTF-8')
        except locale.Error:
            locale.setlocale(locale.LC_ALL, 'C.UTF-8')

        nomes_ordenados.sort(key=locale.strxfrm)
        
        return nomes_ordenados

    def obter_estatisticas_atleta(self, nome_atleta):
        estatisticas = {
            "Gols Regulares": 0, "Gols de Pênalti": 0, "Gols Contra": 0,
            "Cartões Amarelos": 0, "Cartões Vermelhos": 0
        }
        for gol in self.gols:
            if gol.atleta.lower() == nome_atleta.lower():
                tipo = gol.tipo.lower().strip()
                if tipo == "regular":
                    estatisticas["Gols Regulares"] += 1
                elif tipo == "penalti":
                    estatisticas["Gols de Pênalti"] += 1
                elif tipo == "contra":
                    estatisticas["Gols Contra"] += 1
        
        for cartao in self.cartoes:
            if cartao.atleta.lower() == nome_atleta.lower():
                tipo = cartao.tipo.lower().strip()
                if tipo == "amarelo":
                    estatisticas["Cartões Amarelos"] += 1
                elif tipo == "vermelho":
                    estatisticas["Cartões Vermelhos"] += 1
        return estatisticas

    def obter_estatisticas_clube(self, nome_clube):
        estatisticas = {
            "Vitórias": 0, "Derrotas": 0, "Empates": 0,
            "Gols Marcados": 0, "Gols Sofridos": 0
        }
        for partida in self.partidas:
            if partida.mandante.lower() == nome_clube.lower():
                if partida.vencedor and partida.vencedor.lower() == nome_clube.lower():
                    estatisticas["Vitórias"] += 1
                elif partida.vencedor and partida.vencedor.lower() != "empate":
                    estatisticas["Derrotas"] += 1
                else:
                    estatisticas["Empates"] += 1
                estatisticas["Gols Marcados"] += partida.placar_mandante
                estatisticas["Gols Sofridos"] += partida.placar_visitante
            elif partida.visitante.lower() == nome_clube.lower():
                if partida.vencedor and partida.vencedor.lower() == nome_clube.lower():
                    estatisticas["Vitórias"] += 1
                elif partida.vencedor and partida.vencedor.lower() != "empate":
                    estatisticas["Derrotas"] += 1
                else:
                    estatisticas["Empates"] += 1
                estatisticas["Gols Marcados"] += partida.placar_visitante
                estatisticas["Gols Sofridos"] += partida.placar_mandante
        return estatisticas