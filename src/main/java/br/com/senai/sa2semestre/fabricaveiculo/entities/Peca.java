package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Representa a peça a ser utilizada na montagem do veículo
 */
@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeca;
    private String nome;
    private String descricao;

    private int quantidadePecas;
    @OneToMany(mappedBy = "peca")
    private List<VeiculoPeca> listaDeVeiculosComEssaPeca;

    @OneToMany(mappedBy = "peca")
    private List<Estoque> listaDePecasEmEstoque;

    @OneToMany(mappedBy = "peca")
    private List<Producao> listaDePecasEmProducao;

    public Peca(){}

    public Peca(Long idPeca, String nome, String descricao, int quantidadePecas, List<VeiculoPeca> listaDeVeiculosComEssaPeca, List<Estoque> listaDePecasEmEstoque, List<Producao> listaDePecasEmProducao) {
        this.idPeca = idPeca;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadePecas = quantidadePecas;
        this.listaDeVeiculosComEssaPeca = listaDeVeiculosComEssaPeca;
        this.listaDePecasEmEstoque = listaDePecasEmEstoque;
        this.listaDePecasEmProducao = listaDePecasEmProducao;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
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

    public int getQuantidadePecas() {
        return quantidadePecas;
    }

    public void setQuantidadePecas(int quantidadePecas) {
        this.quantidadePecas = quantidadePecas;
    }

    public List<VeiculoPeca> getListaDeVeiculosComEssaPeca() {
        return listaDeVeiculosComEssaPeca;
    }

    public void setListaDeVeiculosComEssaPeca(List<VeiculoPeca> listaDeVeiculosComEssaPeca) {
        this.listaDeVeiculosComEssaPeca = listaDeVeiculosComEssaPeca;
    }

    public List<Estoque> getListaDePecasEmEstoque() {
        return listaDePecasEmEstoque;
    }

    public void setListaDePecasEmEstoque(List<Estoque> listaDePecasEmEstoque) {
        this.listaDePecasEmEstoque = listaDePecasEmEstoque;
    }

    public List<Producao> getListaDePecasEmProducao() {
        return listaDePecasEmProducao;
    }

    public void setListaDePecasEmProducao(List<Producao> listaDePecasEmProducao) {
        this.listaDePecasEmProducao = listaDePecasEmProducao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Peca peca = (Peca) o;

        if (quantidadePecas != peca.quantidadePecas) return false;
        if (!idPeca.equals(peca.idPeca)) return false;
        if (!Objects.equals(nome, peca.nome)) return false;
        if (!Objects.equals(descricao, peca.descricao)) return false;
        if (!Objects.equals(listaDeVeiculosComEssaPeca, peca.listaDeVeiculosComEssaPeca))
            return false;
        if (!Objects.equals(listaDePecasEmEstoque, peca.listaDePecasEmEstoque))
            return false;
        return Objects.equals(listaDePecasEmProducao, peca.listaDePecasEmProducao);
    }

    @Override
    public int hashCode() {
        int result = idPeca.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + quantidadePecas;
        result = 31 * result + (listaDeVeiculosComEssaPeca != null ? listaDeVeiculosComEssaPeca.hashCode() : 0);
        result = 31 * result + (listaDePecasEmEstoque != null ? listaDePecasEmEstoque.hashCode() : 0);
        result = 31 * result + (listaDePecasEmProducao != null ? listaDePecasEmProducao.hashCode() : 0);
        return result;
    }

    /**
     * Exibe todas os atributos de peça
     * @return Retorna uma representação em string do objeto Peça
     */
    @Override
    public String toString() {
        return "PEÇA [" +
                "ID Peça: " + idPeca +
                ", Nome: " + nome +
                ", Descrição: " + descricao +
                ", Quantidade de peças: " + quantidadePecas +
                ", Lista de veículos com essa peça: " + listaDeVeiculosComEssaPeca +
                ", Lista de peças em estoque: " + listaDePecasEmEstoque +
                ", Lista de peças em produção: " + listaDePecasEmProducao +
                "]";
    }
}
