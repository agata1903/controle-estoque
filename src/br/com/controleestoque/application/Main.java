package br.com.controleestoque.application;

import br.com.controleestoque.dao.MarcaDAO;
import br.com.controleestoque.model.Marca;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.service.MarcaService;
import br.com.controleestoque.service.ProdutoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MarcaService  marcaService = new MarcaService();
        ProdutoService produtoService = new ProdutoService();

        String marcaEscolhida;
        int opcao, opcaoCadastro, opcaoBusca, opcaoAlteracao, opcaoAlteracaoMarca, opcaoAlteracaoProduto, opcaoRemocao;
        boolean confirmaCadastro = false, nomeExiste = false;

        do {
            System.out.println("CONTROLE DE ESTOQUE");
            System.out.println("ESCOLHA UMA OPÇÃO: \n1- CADASTRAR\n2- BUSCAR\n3- ALTERAR\n4- REMOVER\n0- SAIR:");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.println("ESCOLHA UMA OPÇÃO DE CADASTRO:\n1- MARCA\n2- PRODUTO\n0- VOLTAR:");
                    opcaoCadastro = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoCadastro) {
                        case 1 -> {
                            try {
                                System.out.println("NOME: ");
                                String nome = scanner.nextLine();
                                Marca marca = new Marca();
                                marca.setNome(nome);
                                marcaService.adicionarMarca(marca);
                                System.out.println("MARCA CADASTRADA COM SUCESSO!");
                            }
                            catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case 2 -> {
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
                                        produtoService.existeProduto(nome);
                                        break;
                                    }
                                    catch (Exception e) {
                                        System.out.println("ERRO: " + e.getMessage());
                                    }
                                }

                                System.out.println("ESTOQUE: ");
                                int estoque = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("PREÇO: ");
                                double preco = scanner.nextDouble();
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

                        case 0 -> {}
                        default -> System.out.println("OPÇÃO INVÁLIDA!");
                    }
                }

                case 2 -> {
                    System.out.println("ESCOLHA UMA OPÇÃO DE BUSCA: 1- MARCA, 2- PRODUTO, 0- VOLTAR:");
                    opcaoBusca = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoBusca) {
                        case 1 -> {
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

                        case 2 -> {
                            try {
                                System.out.println("NOME: ");
                                String nome = scanner.nextLine();
                                Produto p = produtoService.mostrarProduto(nome);
                                System.out.println("PRODUTO ENCONTRADO!");
                                System.out.println("NOME: " + p.getNome() +
                                        "\nESTOQUE: " + p.getEstoque());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        case 0 -> {}
                        default -> System.out.println("OPÇÃO INVÁLIDA!");
                    }
                }

                case 3 -> {
                    System.out.println("ESCOLHA O QUE DESEJA ALTERAR: 1- MARCA, 2- PRODUTO, 0- VOLTAR:");
                    opcaoAlteracao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoAlteracao) {
                        case 1 -> {
                            try {
                                System.out.println("QUAL MARCA DESEJA FAZER UMA ALTERAÇÃO?");
                                marcaEscolhida = scanner.nextLine();
                                marcaService.existePorNome(marcaEscolhida);
                                System.out.println("MARCA ENCONTRADA!");
                            }
                            catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                            System.out.println("QUAL OPÇÃO DESEJA? 1- CADASTRAR NOVO PRODUTO, 2- ALTERAR PRODUTO, " +
                                    "3- REMOVER MARCA, 0- VOLTAR:");
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
                                    System.out.println("QUE TIPO DE ALTERAÇÃO GOSTARIA DE FAZER? 1- NOME, 2- ESTOQUE, 3- PREÇO, 0- VOLTAR:");
                                }

                                case 0 -> {}
                                default -> System.out.println("OPÇÃO INVÁLIDA!");
                            }
                        }
                    }

                }

                case 4 -> {
                    System.out.println("ESCOLHA O QUE DESEJA REMOVER: 1- MARCA, 2- PRODUTO, 0- VOLTAR:");
                    opcaoRemocao = scanner.nextInt();
                }

                case 0 -> System.out.println("ENCERRANDO SISTEMA...");

                default -> System.out.println("OPÇÃO INVÁLIDA!");
            }
        }

        while (opcao != 0);

        scanner.close();

    }
}