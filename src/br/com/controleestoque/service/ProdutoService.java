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

        if (produtoDAO.buscarPorNome(produto.getNome()) != null) {
            throw new IllegalArgumentException("PRODUTO JÁ EXISTENTE!");
        }

        if (produto.getEstoque() < 0) {
            throw new IllegalArgumentException("ESTOQUE NÃO PODE SER NEGATIVO!");
        }

        if (produto.getEstoque() > 50) {
            throw new IllegalArgumentException("QUANTIDADE MAIOR QUE O ESTOQUE MÁXIMO!");
        }

        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("PREÇO INVÁLIDO!");
        }

        if (!marcaDAO.existePorId(produto.getMarcaId())) {
            throw new IllegalArgumentException("MARCA NÃO ENCONTRADA!");
        }

        produtoDAO.adicionarProduto(produto);
    }

    public Produto buscarProdutoPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("NOME INVÁLIDO!");
        }

        Produto produto = produtoDAO.buscarPorNome(nome);

        if (produto == null) {
            throw new IllegalArgumentException("PRODUTO NÃO ENCONTRADO!");
        }

        return produto;
    }

    public Produto buscarProdutoPorId(int id) {
        Produto produto = produtoDAO.buscarPorId(id);

        return produto;
    }


    public void validarProdutoExistente(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("NOME INVÁLIDO!");
        }
        if (produtoDAO.buscarPorNome(nome) != null) {
            throw new IllegalArgumentException("PRODUTO JÁ EXISTENTE!");
        }

    }

    public Produto buscarProdutoPorMarca(Marca marca) {
        if (marca == null) {
            throw new IllegalArgumentException("MARCA NÃO EXISTENTE!");
        }

        if (marca.getNome() == null ||  marca.getNome().isBlank()) {
            throw new IllegalArgumentException("NOME INVÁLIDO!");
        }

        return produtoDAO.buscarPorId(marca.getId());
    }


    public void alterarNome(int id, String novoNome) {
        if (novoNome == null || novoNome.isBlank()) {
            throw new IllegalArgumentException("NOME INVÁLIDO!");
        }
        produtoDAO.alterarNomeProduto(id, novoNome);
    }

    public void alterarEstoque(int id, int novoEstoque) {
        if (novoEstoque > 50) {
            throw new IllegalArgumentException("QUANTIDADE MÁXIMA DE ESTOQUE!");
        }

        produtoDAO.alterarEstoqueProduto(id, novoEstoque);
    }

    public void alterarPreco(int id, double novoPreco) {
        if (novoPreco <= 0) {
            throw new IllegalArgumentException("PREÇO INVÁLIDO!");
        }

        produtoDAO.alterarPrecoProduto(id, novoPreco);

    }
}
