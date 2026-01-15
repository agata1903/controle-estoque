package br.com.controleestoque.service;

import br.com.controleestoque.dao.MarcaDAO;
import br.com.controleestoque.model.Marca;

public class MarcaService {

    MarcaDAO marcaDAO = new MarcaDAO();

    public void adicionarMarca(Marca marca) {

        if (marca.getNome().isBlank()) {
            throw new IllegalArgumentException("NOME NÃO PODE ESTAR EM BRANCO.");
        }

        if(marcaDAO.existePorNome(marca.getNome())) {
            throw new IllegalArgumentException("MARCA JÁ EXISTENTE.");
        }

        marcaDAO.adicionarMarca(marca);
    }

    public void existePorId(int id) {
        if (!marcaDAO.existePorId(id)) {
            throw new IllegalArgumentException("MARCA NÃO ENCONTRADA COM ESSE ID.");
        }

    }

    public void existePorNome(String nome) {
        if (!marcaDAO.existePorNome(nome)) {
            throw new IllegalArgumentException("MARCA NÃO ENCONTRADA COM ESSE NOME.");
        }

    }
}
