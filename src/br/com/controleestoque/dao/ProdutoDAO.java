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

    //Mostrar produtos da busca por nome
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
            throw new IllegalArgumentException("PROBLEMA AO MOSTRAR O NOME DO PRODUTO", e);
        }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection connection = ConexaoMySQL.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
                String nomeProduto = rs.getString("nome");
                int estoque = rs.getInt("estoque");
                double preco = rs.getDouble("preco");
                int marcaId = rs.getInt("marca_id");

                return new Produto(id, nomeProduto, estoque, preco, marcaId);
            }
            return null;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("PROBLEMA AO MOSTRAR O ID DO PRODUTO", e);
        }
    }

    //Alterar nome de produto
    public void alterarNomeProduto(int id, String novoNome) {
        String sql = "UPDATE produto SET nome = ? WHERE id = ?";

        try (Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("NOME ALTERADO COM SUCESSO!");
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("PROBLEMA AO ALTERAR O NOME DO PRODUTO", e);
        }
    }
    //Alterar estoque de produto
    public void alterarEstoqueProduto(int id, int novoEstoque) {
        String sql = "UPDATE produto SET estoque = ? WHERE id = ?";

        try(Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, novoEstoque);
            stmt.setInt(2, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("ESTOQUE ALTERADO COM SUCESSO!");
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("PROBLEMA AO ALTERAR O ESTOQUE DO PRODUTO", e);
        }
    }

    //Alterar preço de produto
    public void alterarPrecoProduto(int id, double novoPreco) {
        String sql = "UPDATE produto SET preco = ? WHERE id = ?";
        try (Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, novoPreco);
            stmt.setInt(2, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("PREÇO ALTERADO COM SUCESSO!");
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("PROBLEMA AO ALTERAR O PREÇO DO PRODUTO", e);
        }
    }

    public boolean existeProdutoComMarca(int marcaId) {
        String sql = "SELECT 1 FROM produto WHERE marca_id = ? LIMIT 1";

        try (Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, marcaId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e) {
            throw new RuntimeException("ERRO AO VERIFICAR PRODUTOS DA MARCA", e);
        }
    }
}