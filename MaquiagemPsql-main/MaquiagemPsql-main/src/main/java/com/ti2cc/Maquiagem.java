package com.ti2cc; 

public class Maquiagem {
    private int codigo;
    private String regiao; // olho, boca, bochecha
    private String marca; // mac, nars, kiko
    private String produto; // rimel, batom, blush

    public Maquiagem() {
        this.codigo = -1;
        this.regiao = "";
        this.marca = "";
        this.produto = "";
    }

    public Maquiagem(int codigo, String regiao, String marca, String produto) {
        this.codigo = codigo;
        this.regiao = regiao;
        this.marca = marca;
        this.produto = produto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Maquiagem [codigo=" + codigo + ", regiao=" + regiao + ", marca=" + marca + ", produto=" + produto + "]";
    }
}
