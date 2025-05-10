package modelos;

class NoB {
    // Array para armazenar as chaves (palavras) no nó
    String[] chaves;

    // Array para armazenar os filhos (outros nós) desse nó
    NoB[] filhos;

    // Número atual de chaves presentes no nó
    int numChaves;

    // Indica se este nó é uma folha (não tem filhos)
    boolean folha;

    public NoB(int grau, boolean folha) {
        // Um nó pode ter no máximo 2*grau-1 chaves
        this.chaves = new String[2 * grau - 1];

        // Um nó pode ter no máximo 2*grau filhos
        this.filhos = new NoB[2 * grau];

        // Inicialmente o nó está vazio
        this.numChaves = 0;

        // Define se é folha ou não
        this.folha = folha;
    }
}