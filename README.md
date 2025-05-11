# **Árvore B em Java**

## 📋 Descrição

Este projeto implementa uma **Árvore B** em Java. A Árvore B é uma estrutura de dados balanceada que mantém dados ordenados e permite buscas, inserções, remoções e navegações eficientes. A implementação foi feita sem o uso de coleções prontas ou arrays de tamanho fixo.

O objetivo foi desenvolver uma **Árvore B** com regras rigorosas, realizando as operações de forma manual e sem depender de implementações prontas como listas ou arrays dinâmicos. A árvore possui operações como **inserção**, **busca**, **remoção** e **impressão** da estrutura.

---

## 🛠️ Estrutura do Código

- **ArvoreB.java**
  Representa a árvore B, contendo:

  - `raiz` (Raiz da árvore)
  - `grau` (Grau mínimo da árvore, que define a capacidade dos nós)
  - Métodos:

    - `inserir(String chave)` → Insere uma chave na árvore.
    - `buscar(String chave)` → Realiza a busca por uma chave.
    - `remover(String chave)` → Remove uma chave da árvore.
    - `imprimir()` → Imprime a estrutura da árvore.

- **NoB.java**
  Representa um nó da árvore B, contendo:

  - `chaves` (Array de chaves armazenadas no nó)
  - `filhos` (Array de filhos, caso o nó não seja folha)
  - `numChaves` (Número de chaves presentes no nó)
  - `folha` (Indica se o nó é uma folha)

- **Main.java**
  Interface de interação no console:

  - Menu para o usuário escolher entre inserir, buscar, remover ou imprimir a árvore.
  - Exemplo de uso das funcionalidades da árvore B.

---

## 🎮 Como Rodar

1. Compile os arquivos `.java`:

   ```bash
   javac *.java
   ```

2. Execute o programa:

   ```bash
   java Main
   ```

---

## 👨‍💻 Integrantes

- Guilherme Tuchanski Rocha
- Luiz Matoso

## ✅ Regras Seguidas

- ❌ Não usamos arrays, listas prontas ou coleções.
- ❌ Não usamos StringBuilder, ArrayList, LinkedList, etc.
- ✅ Usamos apenas `String`, `int`, `try-catch`, `throws`, e manipulação manual de nós.
- ✅ `length` foi utilizado apenas para Strings.
- ✅ Implementação 100% manual da estrutura em árvore binária.
