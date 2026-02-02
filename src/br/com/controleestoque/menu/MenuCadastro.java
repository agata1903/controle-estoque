package br.com.controleestoque.menu;

import br.com.controleestoque.model.Marca;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.service.MarcaService;
import br.com.controleestoque.service.ProdutoService;

import java.util.Scanner;

public class MenuCadastro {

    public static void exibirMenuCadastro(Scanner scanner, ProdutoService produtoService, MarcaService marcaService) {
        int opcao;

        do {
            System.out.println("O QUE DESEJA CADASTRAR? 1- MARCA, 2- PRODUTO, 3- VOLTAR:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarMarca(scanner, marcaService);
                case 2 -> cadastrarProduto(scanner, produtoService, marcaService);
                case 0 -> {}
                default -> System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
        while (opcao != 0);
        }


    private static void cadastrarMarca(Scanner scanner, MarcaService marcaService) {
        try {
            System.out.println("NOME: ");
            String nome = scanner.nextLine();

            Marca marca = new Marca();
            marca.setNome(nome);
            marcaService.adicionarMarca(marca);

            System.out.println("MARCA CADASTRADA COM SUCESSO!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastrarProduto(
            Scanner scanner,
            ProdutoService produtoService,
            MarcaService marcaService
    ) {
        try {
            System.out.println("DE QUAL MARCA ESTE PRODUTO SERÁ?");
            int marcaId = scanner.nextInt();
            scanner.nextLine();

            marcaService.existePorId(marcaId);
            System.out.println("MARCA ENCONTRADA.");

            String nome;
            while (true) {
                try {
                    System.out.println("NOME: ");
                    nome = scanner.nextLine();
                    produtoService.validarProdutoExistente(nome);
                    break;
                } catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }

            System.out.println("ESTOQUE: ");
            int estoque = scanner.nextInt();
            scanner.nextLine();

            System.out.println("PREÇO: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();

            Produto produto = new Produto(nome, estoque, preco, marcaId);
            produtoService.adicionarProduto(produto);

            System.out.println("PRODUTO CADASTRADO COM SUCESSO!");

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}
