package br.com.controleestoque.dao;

import br.com.controleestoque.connection.ConexaoMySQL;
import br.com.controleestoque.model.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarcaDAO {

    //Adicionar marca
    public void adicionarMarca(Marca marca) {

        String sql = "INSERT INTO marca (nome) VALUES (?)";

        try (Connection conn = ConexaoMySQL.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, marca.getNome());
            stmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Erro");
        }
    }

    public boolean existePorId(int id) {

        String sql = "SELECT 1 FROM marca WHERE id = ?";
        try (Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
        catch (SQLException e) {
            throw new RuntimeException("ERRO AO VERIFICAR MARCA", e);
        }
    }

    public boolean existePorNome(String nome) {
        String sql = "SELECT 1 FROM marca WHERE nome = ?";

        try(Connection connection = ConexaoMySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        }
        catch (SQLException e) {
            throw new RuntimeException("MARCA NÃO ENCONTRADA", e);
        }
    }


}
