package com.eliasfs06.imdmarket.model;

public class Produto {

    String codigo;
    String nome;
    String descricao;
    int estoque;

    public Produto() {
    }

    public Produto(String codigo, String nome, String descricao, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.estoque = estoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
