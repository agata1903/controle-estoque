package br.com.controleestoque.model;

import java.util.List;

public class Marca {

    private int id;
    private String nome;
    private List<Produto> produtos;

    public Marca() {
    }

    public Marca(String nome, List<Produto> produtos) {
        this.nome = nome;
        this.produtos = produtos;
    }

    public Marca(int id, String nome, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
