package br.com.controleestoque.service;

import br.com.controleestoque.dao.MarcaDAO;
import br.com.controleestoque.dao.ProdutoDAO;
import br.com.controleestoque.model.Produto;

public class ProdutoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MarcaDAO marcaDAO = new MarcaDAO();

    public void adicionarProduto(Produto produto) {

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("NOME INVÁLIDO!");
        }
        if (produto.getEstoque() < 0) {
            throw new IllegalArgumentException("ESTOQUE NÃO PODE SER NEGATIVO!");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("PREÇO INVÁLIDO!");
        }

        boolean marcaExiste = marcaDAO.existePorId(produto.getMarcaId());

        if (!marcaExiste) {
            throw new RuntimeException("MARCA NÃO ENCONTRADA!");
        }

        if (!marcaDAO.existePorId(produto.getMarcaId())) {
            throw new IllegalArgumentException("MARCA NÃO ENCONTRADA PARA O ID INFORMADO!");
        }

        produtoDAO.adicionarProduto(produto);
    }

    public Produto mostrarProduto(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("NOME INVÁLIDO!");
        }

        Produto produto = produtoDAO.buscarPorNome(nome);

        if (produto == null) {
            throw new IllegalArgumentException("PRODUTO NÃO ENCONTRADO!");
        }
        return produto;
    }



}