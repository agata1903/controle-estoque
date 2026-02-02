package br.com.controleestoque.menu;

import br.com.controleestoque.model.Produto;
import br.com.controleestoque.service.MarcaService;
import br.com.controleestoque.service.ProdutoService;

import java.util.Scanner;

public class MenuBusca {

    public static void exibirMenuBusca(Scanner scanner, ProdutoService produtoService, MarcaService marcaService) {
        int opcao;

        do {
            System.out.println("O QUE DESEJA BUSCAR? 1- MARCA, 2- PRODUTO:");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> buscarMarca(scanner, marcaService);
                case 2 -> buscarProduto(scanner, produtoService);
                case 0 -> {}
                default -> System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
        while (opcao != 0);
    }

    public static void buscarMarca(Scanner scanner, MarcaService marcaService) {
        try {
            System.out.println("NOME: ");
            String nome = scanner.nextLine();
            marcaService.existePorNome(nome);
            System.out.println("MARCA ENCONTRADA!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void buscarProduto(Scanner scanner, ProdutoService produtoService) {
        try {
            System.out.println("NOME: ");
            String nome = scanner.nextLine();
            Produto p = produtoService.buscarProdutoPorNome(nome);
            System.out.println("PRODUTO ENCONTRADO!");
            System.out.println("NOME: " + p.getNome() +
                    "\nESTOQUE: " + p.getEstoque());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
