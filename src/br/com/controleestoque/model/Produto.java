package br.com.controleestoque.model;

public class Produto {

    private int id;
    private String nome;
    private int estoque;
    private double preco;
    private int marcaId;

    public Produto() {
    }

    public Produto(String nome, int estoque, double preco, int marcaId) {
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.marcaId = marcaId;
    }

    public Produto(int id, String nome, int estoque, double preco, int marcaId) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.marcaId = marcaId;
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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }
}
