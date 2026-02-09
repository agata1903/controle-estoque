package br.com.controleestoque.menu;

import br.com.controleestoque.service.MarcaService;
import br.com.controleestoque.service.ProdutoService;

import java.util.Scanner;

import static br.com.controleestoque.menu.MenuAlteracao.exibirMenuAlteracao;
import static br.com.controleestoque.menu.MenuBusca.exibirMenuBusca;
import static br.com.controleestoque.menu.MenuCadastro.exibirMenuCadastro;

public class MenuPrincipal {

    private Scanner scanner;
    private ProdutoService produtoService;
    private MarcaService marcaService;

    public MenuPrincipal(Scanner scanner, ProdutoService produtoService, MarcaService marcaService) {
        this.scanner = scanner;
        this.produtoService = produtoService;
        this.marcaService = marcaService;
    }

    public void exibirMenuPrincipal() {
        int opcao;
        System.out.println("O QUE DESEJA FAZER: 1- CADASTRAR, 2- BUSCAR, 3- ALTERAR, 0- SAIR:");
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> exibirMenuCadastro(scanner, produtoService, marcaService);

            case 2 -> exibirMenuBusca(scanner, produtoService, marcaService);

            case 3 -> exibirMenuAlteracao(scanner, produtoService, marcaService);

            case 0 -> System.out.println("ENCERRANDO SISTEMA...");
        }
    }
}
