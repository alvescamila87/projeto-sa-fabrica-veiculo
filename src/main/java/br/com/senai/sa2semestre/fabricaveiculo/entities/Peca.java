package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Representa a peça
 */
@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeca;
    private String nome;
    private String descricao;

    public Peca(){}

    public Peca(Long idPeca, String nome, String descricao) {
        this.idPeca = idPeca;
        this.nome = nome;
        this.descricao = descricao;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Peca pecas = (Peca) o;

        if (!idPeca.equals(pecas.idPeca)) return false;
        if (!Objects.equals(nome, pecas.nome)) return false;
        return Objects.equals(descricao, pecas.descricao);
    }

    @Override
    public int hashCode() {
        int result = idPeca.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
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
                "]";
    }
}
