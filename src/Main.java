import Entities.Client;
import Repositories.ClientRepository;
import Repositories.FileClientRepository;
import Services.ClientService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String path = "ADICIONE UMA PASTA AQUI";

        ClientRepository repo = new FileClientRepository(path);
        ClientService service = new ClientService(repo);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n===============================");
            System.out.println("   GESTOR DE CLIENTES (FILE)   ");
            System.out.println("===============================");
            System.out.println("1. Novo Cadastro");
            System.out.println("2. Listar Todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Remover Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO CADASTRO ---");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();
                    service.create(nome, email, tel);
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE CLIENTES ---");
                    List<Client> todos = service.listAll();
                    if (todos.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        todos.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("\nDigite o ID para busca: ");
                    long idBusca = Long.parseLong(scanner.nextLine());
                    Client c = service.getById(idBusca);
                    if (c != null) {
                        System.out.println("Cliente encontrado: " + c);
                    }
                    break;

                case 4:
                    System.out.print("\nDigite o ID do cliente que deseja atualizar: ");
                    long idUpdate = Long.parseLong(scanner.nextLine());
                    // Verifica se existe antes de pedir os novos dados
                    if (service.getById(idUpdate) != null) {
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo Email: ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Novo Telefone: ");
                        String novoTel = scanner.nextLine();
                        service.updateInfo(idUpdate, novoNome, novoEmail, novoTel);
                    }
                    break;

                case 5:
                    System.out.print("\nDigite o ID para remover: ");
                    long idRemover = Long.parseLong(scanner.nextLine());
                    service.remove(idRemover);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        scanner.close();
    }
}