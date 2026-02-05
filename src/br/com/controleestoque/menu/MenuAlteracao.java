package br.com.controleestoque.menu;

import br.com.controleestoque.model.Produto;
import br.com.controleestoque.service.MarcaService;
import br.com.controleestoque.service.ProdutoService;

import java.util.Scanner;

public class MenuAlteracao {

    public static void exibirMenuAlteracao(Scanner scanner, MarcaService marcaService, ProdutoService produtoService) {
        int opcao;

        do {
            System.out.println("O QUE DESEJA ALTERAR? 1- MARCA, 2- PRODUTO, 0- VOLTAR:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> alterarMarca(scanner, produtoService, marcaService);
                case 2 -> alterarProduto(scanner, produtoService,marcaService);
                case 0 -> {}
                default -> System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
        while (opcao != 0);
    }

    public static void alterarMarca(Scanner scanner, MarcaService marcaService, ProdutoService produtoService) {
        String marcaEscolhida;
        int opcaoAlteracaoMarca;
        boolean confirmaCadastro = false;

        while (true) {
            try {
                System.out.println("QUAL MARCA DESEJA FAZER UMA ALTERAÇÃO?");
                marcaEscolhida = scanner.nextLine();
                marcaService.existePorNome(marcaEscolhida);
                System.out.println("MARCA ENCONTRADA!");
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("QUAL OPÇÃO DESEJA? 1- CADASTRAR NOVO PRODUTO, 2- REMOVER MARCA, 0- VOLTAR:");
        opcaoAlteracaoMarca = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoAlteracaoMarca) {
            case 1 -> {
                try {
                    System.out.println("NOME: ");
                    String nome = scanner.nextLine();
                    System.out.println("ESTOQUE: ");
                    int estoque = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("PREÇO: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("DE QUAL MARCA ESTE PRODUTO SERÁ?");
                    int marcaId = scanner.nextInt();
                    scanner.nextLine();
                    Produto p1 = new Produto(nome, estoque, preco, marcaId);
                    produtoService.adicionarProduto(p1);
                    confirmaCadastro = true;
                    if (confirmaCadastro) {
                        System.out.println("PRODUTO CADASTRADO COM SUCESSO!");
                    }

                } catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }

            case 2 -> {
                try {
                    marcaService.removerMarcaPorNome(marcaEscolhida);
                    System.out.println("MARCA REMOVIDA COM SUCESSO!");
                }
                catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            case 0 -> {}
            default -> System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    public static void alterarProduto(Scanner scanner, ProdutoService produtoService, MarcaService marcaService) {
        String marcaEscolhida;
        while (true) {
            try {
                System.out.println("DE QUAL MARCA GOSTARIA DE ALTERAR UM PRODUTO?");
                marcaEscolhida = scanner.nextLine();
                marcaService.existePorNome(marcaEscolhida);
                System.out.println("MARCA ENCONTRADA!");
                break;
            }
            catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }
        System.out.println("ESTES SÃO OS PRODUTOS DA MARCA " + marcaEscolhida);
        Marca marca = marcaService.buscarPorNome(marcaEscolhida);
        produtoService.listarProdutosPorMarca(marca.getId());

        Produto p;
        while (true) {
            try {
                System.out.println("QUAL PRODUTO DESEJA ALTERAR?");
                produtoEscolhido = scanner.nextLine();
                p = produtoService.buscarProdutoPorNome(produtoEscolhido);
                System.out.println("PRODUTO ENCONTRADO!");
                break;
            }
            catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }

        System.out.println("O QUE DESEJA FAZER COM ESSE PRODUTO? 1- ALTERAR NOME, 2- ALTERAR ESTOQUE, " +
                "3- ALTERAR PREÇO, 4- REMOVER PRODUTO, 0- VOLTAR");
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                try {
                    System.out.println("NOVO NOME: ");
                    String novoNome = scanner.nextLine();
                    produtoService.validarProdutoExistente(novoNome);
                    produtoService.alterarNome(p.getId(), novoNome);
                }
                catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            case 2 -> {
                try {
                    System.out.println("QUANTO DESEJA ADICIONAR AO ESTOQUE? ");
                    int novoEstoque = scanner.nextInt();
                    scanner.nextLine();
                    produtoService.validarAlteracaoEstoque(p, novoEstoque);
                    produtoService.alterarEstoque(p, novoEstoque);
                }
                catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
            case 3 -> {
                try {
                    System.out.println("QUAL O NOVO PREÇO DO PRODUTO? ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine();
                    produtoService.alterarPreco(p.getId(), novoPreco);
                }
                catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
        }
    }
}
