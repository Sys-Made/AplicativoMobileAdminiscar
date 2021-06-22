package com.example.aplicativomobileadminiscar.PRODUTO;

public class model
{
    String modelo,combustivel,diaria,imagem;
    model()
    {

    }
    public model(String modelo, String combustivel, String diaria, String imagem) {
        this.modelo = modelo;
        this.combustivel = combustivel;
        this.diaria = diaria;
        this.imagem = imagem;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getDiaria() {
        return diaria;
    }

    public void setDiaria(String diaria) {
        this.diaria = diaria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
