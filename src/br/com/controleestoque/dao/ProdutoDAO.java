package br.com.controleestoque.dao;

import br.com.controleestoque.connection.ConexaoMySQL;
import br.com.controleestoque.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {

    //AdicionarProduto
    public void adicionarProduto(Produto produto) {

        String sql = "INSERT INTO produto (nome, estoque, preco, marcaId) VALUES (?,?,?,?)";

        try (Connection connection = ConexaoMySQL.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getEstoque());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getMarcaId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
