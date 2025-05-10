package modelos;

public class ArvoreB {
    private NoB raiz;  // Raiz da árvore
    private int grau;   // Grau mínimo da árvore B (define a capacidade dos nós)

    // Construtor da árvore
    public ArvoreB(int grau) {
        this.raiz = null;  // Árvore começa vazia
        this.grau = grau;   // Define o grau mínimo
    }

    // Método público para inserir uma chave
    public void inserir(String chave) {
        // Caso a árvore esteja vazia
        if (raiz == null) {
            raiz = new NoB(grau, true);  // Cria nova raiz (folha)
            raiz.chaves[0] = chave;      // Insere a chave
            raiz.numChaves = 1;           // Atualiza contador
        } else {
            // Se a raiz está cheia, precisamos dividi-la
            if (raiz.numChaves == 2 * grau - 1) {
                NoB novoNo = new NoB(grau, false);  // Nova raiz (não folha)
                novoNo.filhos[0] = raiz;            // A raiz antiga vira filho
                dividirNo(novoNo, 0, raiz);         // Divide a raiz antiga

                // Decide em qual filho inserir a nova chave
                int i = 0;
                if (novoNo.chaves[0].compareTo(chave) < 0) {
                    i++;
                }
                inserirNaoCheio(novoNo.filhos[i], chave);

                raiz = novoNo;  // Atualiza a raiz
            } else {
                // Se a raiz não está cheia, insere normalmente
                inserirNaoCheio(raiz, chave);
            }
        }
    }

    // Método auxiliar para inserir em um nó que não está cheio
    private void inserirNaoCheio(NoB no, String chave) {
        int i = no.numChaves - 1;  // Começa do final do nó

        // Se o nó é folha, insere diretamente
        if (no.folha) {
            // Encontra a posição correta e desloca as chaves maiores
            while (i >= 0 && no.chaves[i].compareTo(chave) > 0) {
                no.chaves[i + 1] = no.chaves[i];
                i--;
            }
            // Insere a nova chave na posição correta
            no.chaves[i + 1] = chave;
            no.numChaves++;  // Incrementa contador
        } else {
            // Se não é folha, encontra o filho adequado
            while (i >= 0 && no.chaves[i].compareTo(chave) > 0) {
                i--;
            }
            i++;

            // Se o filho está cheio, divide antes de inserir
            if (no.filhos[i].numChaves == 2 * grau - 1) {
                dividirNo(no, i, no.filhos[i]);
                // Decide qual filho usar após a divisão
                if (no.chaves[i].compareTo(chave) < 0) {
                    i++;
                }
            }
            // Chama recursivamente para o filho selecionado
            inserirNaoCheio(no.filhos[i], chave);
        }
    }

    // Método para dividir um nó cheio
    private void dividirNo(NoB pai, int indice, NoB filho) {
        // Cria novo nó que receberá metade das chaves do filho
        NoB novoNo = new NoB(grau, filho.folha);
        novoNo.numChaves = grau - 1;

        // Copia as chaves maiores do filho para o novo nó
        for (int j = 0; j < grau - 1; j++) {
            novoNo.chaves[j] = filho.chaves[j + grau];
        }

        // Se não for folha, copia também os filhos
        if (!filho.folha) {
            for (int j = 0; j < grau; j++) {
                novoNo.filhos[j] = filho.filhos[j + grau];
            }
        }

        // Reduz o número de chaves do filho
        filho.numChaves = grau - 1;

        // Abre espaço no pai para o novo filho
        for (int j = pai.numChaves; j >= indice + 1; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }

        // Conecta o novo nó como filho do pai
        pai.filhos[indice + 1] = novoNo;

        // Move uma chave do filho para o pai
        for (int j = pai.numChaves - 1; j >= indice; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }

        // A chave do meio do filho sobe para o pai
        pai.chaves[indice] = filho.chaves[grau - 1];
        pai.numChaves++;  // Pai ganha uma nova chave
    }

    // Método público para buscar uma chave
    public void buscar(String chave) {
        if (raiz == null) {
            System.out.println("Árvore vazia");
        } else {
            NoB resultado = buscar(raiz, chave);
            if (resultado != null) {
                System.out.println("Chave " + chave + " encontrada");
            } else {
                System.out.println("Chave " + chave + " não encontrada");
            }
        }
    }

    // Método privado recursivo para buscar uma chave
    private NoB buscar(NoB no, String chave) {
        int i = 0;
        // Encontra a primeira chave maior ou igual à buscada
        while (i < no.numChaves && chave.compareTo(no.chaves[i]) > 0) {
            i++;
        }

        // Se encontrou a chave, retorna o nó
        if (i < no.numChaves && chave.equals(no.chaves[i])) {
            return no;
        }

        // Se é folha e não encontrou, a chave não existe
        if (no.folha) {
            return null;
        }

        // Busca recursivamente no filho apropriado
        return buscar(no.filhos[i], chave);
    }

    public void remover(String chave) {
        if (raiz == null) {
            System.out.println("Árvore vazia");
            return;
        }

        // Chama o método de remoção recursivo
        removerRecursivo(raiz, chave);

        // Se a raiz ficou vazia, atualiza ela
        if (raiz.numChaves == 0) {
            if (raiz.folha) {
                raiz = null;
            } else {
                raiz = raiz.filhos[0];
            }
        }
    }

    private void removerRecursivo(NoB no, String chave) {
        int idx = 0;
        // Encontra a posição da chave ou do filho onde ela estaria
        while (idx < no.numChaves && no.chaves[idx].compareTo(chave) < 0) {
            idx++;
        }

        // Caso 1: A chave está neste nó
        if (idx < no.numChaves && no.chaves[idx].equals(chave)) {
            if (no.folha) {
                // Remove da folha (simples)
                for (int i = idx; i < no.numChaves - 1; i++) {
                    no.chaves[i] = no.chaves[i + 1];
                }
                no.numChaves--;
            } else {
                // Remove de nó interno (mais complexo)
                System.out.println("Remoção de nós internos não implementada nesta versão simplificada");
            }
        } else {
            // Caso 2: A chave está em um filho
            if (no.folha) {
                System.out.println("Chave " + chave + " não encontrada");
                return;
            }

            // Chama recursivamente para o filho apropriado
            removerRecursivo(no.filhos[idx], chave);
        }
    }

    // Método público para imprimir a árvore
    public void imprimir() {
        if (raiz != null) {
            imprimir(raiz, "");
        }
    }

    // Método privado recursivo para imprimir a árvore
    private void imprimir(NoB no, String indentacao) {
        System.out.print(indentacao);
        // Imprime todas as chaves do nó
        for (int i = 0; i < no.numChaves; i++) {
            System.out.print(no.chaves[i] + " ");
        }
        System.out.println();

        // Se não é folha, imprime recursivamente os filhos
        if (!no.folha) {
            for (int i = 0; i <= no.numChaves; i++) {
                if (no.filhos[i] != null) {
                    imprimir(no.filhos[i], indentacao + "    ");
                }
            }
        }
    }
}