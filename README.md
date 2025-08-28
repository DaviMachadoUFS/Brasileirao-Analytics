# Brasileirão Analytics

Aplicação de análise de dados do **Campeonato Brasileiro**, desenvolvida com o objetivo de demonstrar conceitos de **Orientação a Objetos (OO)** em duas linguagens diferentes: **Java** e **Python**.

---

## ⚽ O Problema  
A quantidade de estatísticas sobre o Campeonato Brasileiro é vasta e complexa.  
Extrair, organizar e visualizar dados relevantes exige uma abordagem **modular e bem estruturada**, que facilite o entendimento e a reutilização do código.  

---

## 💻 A Solução  
O projeto consiste em uma **aplicação desktop com interface gráfica**, permitindo:  
- 📊 **Gerar gráficos de barras e pizza** com estatísticas (ex.: vitórias por região).  
- 👤 **Visualizar estatísticas de atletas ou times específicos**.  
- ⚔ **Comparar lado a lado** estatísticas de dois atletas ou times.  

A implementação segue o paradigma de **Orientação a Objetos**, com classes representando entidades como `Partida`, `Gol`, `Cartao`, etc., e **separação clara de responsabilidades** entre as camadas do software.

---

## 📁 Estrutura do Projeto
```
.
├── Projeto em Java/     # Versão Java com JavaFX
└── Projeto em Python/   # Versão Python com PyQt5
```

---

## 🚀 Como Executar o Projeto  

### **Versão Java**  
Requisitos:  
- **JDK 11+**  
- **Maven**  

Passos:  
```bash
cd Projeto em Java/
mvn clean javafx:run
```

---

### **Versão Python**  
Requisitos:  
- **Python 3.6+**  
- Dependências no arquivo `requirements.txt`  

Passos:  
```bash
cd Projeto em Python/
pip install -r requirements.txt
python main.py
```

---

## 🧠 A Orientação a Objetos em Python

A versão em Python mostra como a OO é aplicada de forma mais **flexível**:  

- **Classes e Objetos:** uso de `class` e `__init__` para inicialização, semelhante a construtores em Java.  
- **Encapsulamento:** sem modificadores explícitos (`public`, `private`), usando convenções como `_atributo` ou `__atributo`.  
- **Herança e Polimorfismo:** simples e dinâmicos, sem necessidade de interfaces ou classes abstratas formais.  

**Impressão geral:**  
A POO em Python é menos rígida e mais ágil, mas requer disciplina para manter a organização.  
Os princípios fundamentais da OO se mantêm, reforçando que o paradigma é **conceitual** e adaptável.

---

## 📜 Licença  
Este projeto está sob a licença MIT. Sinta-se à vontade para usar e modificar.  

---

## ✉ Contato  
Criado por **[Davi Oliveira Machado](https://github.com/DaviMachadoUFS)** — contribuições são bem-vindas!
