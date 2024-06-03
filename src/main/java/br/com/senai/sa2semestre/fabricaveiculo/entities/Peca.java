package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
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
    @OneToMany(mappedBy = "veiculo_peca")
    private List<VeiculoPeca> listaDeVeiculosPeças;

    public Peca(){}

    public Peca(Long idPeca, String nome, String descricao, List<VeiculoPeca> listaDeVeiculosPeças) {
        this.idPeca = idPeca;
        this.nome = nome;
        this.descricao = descricao;
        this.listaDeVeiculosPeças = listaDeVeiculosPeças;
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

    public List<VeiculoPeca> getListaDeVeiculosPeças() {
        return listaDeVeiculosPeças;
    }

    public void setListaDeVeiculosPeças(List<VeiculoPeca> listaDeVeiculosPeças) {
        this.listaDeVeiculosPeças = listaDeVeiculosPeças;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Peca peca = (Peca) o;

        if (!idPeca.equals(peca.idPeca)) return false;
        if (!Objects.equals(nome, peca.nome)) return false;
        if (!Objects.equals(descricao, peca.descricao)) return false;
        return Objects.equals(listaDeVeiculosPeças, peca.listaDeVeiculosPeças);
    }

    @Override
    public int hashCode() {
        int result = idPeca.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (listaDeVeiculosPeças != null ? listaDeVeiculosPeças.hashCode() : 0);
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
                ", Lista de veículos peças: " + listaDeVeiculosPeças +
                "]";
    }
}
