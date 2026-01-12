package br.com.controleestoque.service;

import br.com.controleestoque.dao.MarcaDAO;
import br.com.controleestoque.dao.ProdutoDAO;
import br.com.controleestoque.model.Produto;

public class ProdutoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MarcaDAO marcaDAO = new MarcaDAO();

    public void adicionarProduto(Produto produto) {

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (produto.getEstoque() < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo!");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço inválido!");
        }

        boolean marcaExiste = marcaDAO.existePorId(produto.getMarcaId());

        if (!marcaExiste) {
            throw new RuntimeException("Marca não encontrada!");
        }
        produtoDAO.adicionarProduto(produto);
    }
}
