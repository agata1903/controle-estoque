package br.com.controleestoque.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/controle_estoque",
                    "root", "12345");
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco", e);
        }
    }

}
