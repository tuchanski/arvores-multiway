import java.util.Scanner;
import modelos.ArvoreB;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o grau da árvore B: ");
        int grau = Integer.parseInt(scanner.nextLine());

        ArvoreB arvore = new ArvoreB(grau);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Inserir chave");
            System.out.println("2 - Buscar chave");
            System.out.println("3 - Imprimir árvore");
            System.out.println("4 - Remover chave");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1":
                        System.out.print("Digite a chave a ser inserida: ");
                        String chaveInserir = scanner.nextLine();
                        arvore.inserir(chaveInserir);
                        break;
                    case "2":
                        System.out.print("Digite a chave a ser buscada: ");
                        String chaveBuscar = scanner.nextLine();
                        arvore.buscar(chaveBuscar);
                        break;
                    case "3":
                        arvore.imprimir();
                        break;
                    case "4":  // Nova opção de remoção
                        System.out.print("Digite a chave a ser removida: ");
                        String chaveRemover = scanner.nextLine();
                        arvore.remover(chaveRemover);
                        break;
                    case "5":
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}