package br.com.controleestoque.application;

import br.com.controleestoque.dao.MarcaDAO;
import br.com.controleestoque.model.Marca;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.service.ProdutoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MarcaDAO marcaDAO = new MarcaDAO();
        ProdutoService produtoService = new ProdutoService();

        int opcao;

        do {
            System.out.println("CONTROLE DE ESTOQUE");
            System.out.println("ESCOLHA UMA OPÇÃO DE CADASTRO: 1- MARCA, 2- PRODUTO:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("NOME: ");
                    String nome = scanner.next();
                    Marca marca = new Marca();
                    marca.setNome(nome);
                    marcaDAO.adicionarMarca(marca);
                    System.out.println("MARCA CADASTRADA COM SUCESSO!");
                }
                case 2 -> {
                    try {
                        System.out.println("NOME: ");
                        String nome = scanner.next();
                        System.out.println("ESTOQUE: ");
                        int estoque = scanner.nextInt();
                        System.out.println("PREÇO: ");
                        double preco = scanner.nextDouble();
                        System.out.println("DE QUAL MARCA ESTE PRODUTO SERÁ?");
                        int marcaId = scanner.nextInt();
                        Produto p1 = new Produto(nome, estoque, preco, marcaId);
                        produtoService.adicionarProduto(p1);
                        System.out.println("PRODUTO CADASTRADO COM SUCESSO!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }

                }
                case 0 -> System.out.println("ENCERRANDO SISTEMA...");

                default -> System.out.println("OPÇÃO INVÁLIDA!");
            }
        }

        while (opcao != 0);

        scanner.close();

    }
}