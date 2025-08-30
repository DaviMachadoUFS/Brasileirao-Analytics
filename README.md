# Projeto de POO: Análise de Dados do Brasileirão

## 1. Membros da Equipe
- Davi Oliveira Machado

---

## 2. Link para o Repositório
https://github.com/DaviMachadoUFS/Brasileirao-Analytics

---

## 3. Descrição do Tema
Este projeto tem como tema a **análise e visualização de dados do Campeonato Brasileiro** a partir de arquivos CSV.  
O objetivo é **aplicar conceitos de Orientação a Objetos (POO)** para desenvolver uma ferramenta que processa e apresenta informações estatísticas do torneio de forma organizada.

---

## 4. O que Conseguimos Desenvolver
Desenvolvemos uma **aplicação desktop** que permite gerar gráficos e realizar comparações.  
A solução final é capaz de:

- Carregar e processar dados de partidas, gols e cartões;  
- Gerar **gráficos de vitórias por região** (barras e pizza);  
- Exibir **estatísticas detalhadas de atletas e times individuais**;  
- **Comparar estatísticas** de dois atletas ou dois times lado a lado.  

A aplicação foi implementada em **duas linguagens**:
- **Java** (com JavaFX)  
- **Python** (com PyQt5)  

Demonstrando, assim, a aplicação dos princípios de POO em ambos os ambientes.

---

## 5. Discussão sobre a POO em Python
A Orientação a Objetos em **Python** apresenta uma abordagem mais **flexível** em comparação à rigidez do **Java**.

- **Encapsulamento:** Em vez de modificadores explícitos (`public`, `private`), Python adota **convenções**.  
  - Atributos “privados” são indicados com `_atributo` ou `__atributo`.  

- **Polimorfismo:** Implementado de forma **natural**, sem exigir interfaces ou classes abstratas.  
  - Métodos com o mesmo nome em classes diferentes podem ser chamados, e o comportamento adequado é determinado **em tempo de execução**.  

- **Tipagem Dinâmica:**  
  - A ausência de tipagem estática acelera o desenvolvimento, mas **exige atenção** para manter o código limpo e organizado.  

Essas características tornam a **POO em Python menos verbosa e mais direta**, proporcionando uma experiência de aprendizado rica e comparativa com o Java.

---

## 6. Orientações para rodar a aplicação
Para abrir a aplicação em Java basta ter o maven e o Java instalado e seguir os passos:
````bash
cd Projeto em Java/
mvn clean javafx:run
````

Para abrir a aplicação em Python basta ter o PyQt5 instalado e seguir os passos:
````bash
cd Projeto em Python/
python main.py
````