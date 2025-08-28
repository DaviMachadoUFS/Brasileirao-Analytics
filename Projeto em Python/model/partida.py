from dataclasses import dataclass

@dataclass
class Partida:
    mandante: str
    visitante: str
    vencedor: str
    placar_mandante: int
    placar_visitante: int
    vencedor_estado: str