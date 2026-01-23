package br.com.controleestoque.dao;

import br.com.controleestoque.connection.ConexaoMySQL;
import br.com.controleestoque.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    //Adicionar produto
    public void adicionarProduto(Produto produto) {

        String sql = "INSERT INTO produto (nome, estoque, preco, marca_id) VALUES (?,?,?,?)";

        try (Connection connection = ConexaoMySQL.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql))
        {
             stmt.setString(1, produto.getNome());
             stmt.setInt(2, produto.getEstoque());
             stmt.setDouble(3, produto.getPreco());
             stmt.setInt(4, produto.getMarcaId());
             stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERRO" + e.getMessage());
        }
    }

    //Buscar produto por ID
    public boolean buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection connection = ConexaoMySQL.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("PROBLEMA AO BUSCAR O PRODUTO");
        }
    }

    //Mostrar produtos da busca
    public Produto buscarPorNome(String nome) {
        String sql = "SELECT * FROM produto WHERE nome = ?";
        try (Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nomeProduto = rs.getString("nome");
                int estoque = rs.getInt("estoque");
                double preco = rs.getDouble("preco");
                int marcaId = rs.getInt("marca_id");

                return new Produto(id, nomeProduto, estoque, preco, marcaId);

            } else
                return null;
        }

        catch (SQLException e) {
            throw new IllegalArgumentException("PROBLEMA AO MOSTRAR O PRODUTO", e);
        }
    }

    //Alterar produto por nome
    public void alterarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, estoque = ?, preco = ?, marca_id = ? WHERE id = ?";

        try (Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getEstoque());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getMarcaId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("NOME ALTERADO COM SUCESSO");
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("PROBLEMA AO ALTERAR O PRODUTO", e);
        }
    }
}