package com.example;

public class Endereco {
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String logradouro, String bairro, String cidade, String estado) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
