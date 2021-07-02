package com.example.aplicativomobileadminiscar.CRUD;

public class model
{
    String modelo;
    String categoria;
    String diaria;
    String imagem;
    String qtd_dias;
    String pagamento;
    String valor_total;
    String status;

    public model() {
    }
    public model(String modelo, String categoria, String diaria, String imagem, String qtd_dias, String pagamento, String valor_total,String status) {
        this.modelo = modelo;
        this.categoria = categoria;
        this.diaria = diaria;
        this.imagem = imagem;
        this.qtd_dias = qtd_dias;
        this.pagamento = pagamento;
        this.valor_total = valor_total;
        this.status = status;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getQtd_dias() {
        return qtd_dias;
    }

    public void setQtd_dias(String qtd_dias) {
        this.qtd_dias = qtd_dias;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getValor_total() {
        return valor_total;
    }

    public void setValor_total(String valor_total) {
        this.valor_total = valor_total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
