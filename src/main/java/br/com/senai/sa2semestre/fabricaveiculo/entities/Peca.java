package br.com.senai.sa2semestre.fabricaveiculo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa a peça que são os componentes individuais
 * utilizados na linha de producao para a montagem do veículo.
 * A peca está relacionada ao Veículo, Estoque e Produção.
 *
 * <p>
 *  A entidade {@code Peca} está mapeada para a tabela "pecas" no banco de dados.
 *  </p>
 *
 * @see Veiculo
 * @see Estoque
 * @see Producao
 *
 * @author Camila
 *
 * @since V1
 */
@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeca;
    private String nome;
    private String descricao;
    private int quantidadePecas;

    @ManyToMany
    @JsonBackReference
    @JsonIgnore
    private List<Veiculo> listaDeVeiculosComEssaPeca = new ArrayList<>();

    @OneToMany(mappedBy = "peca")
    private List<Estoque> listaDePecasEmEstoque = new ArrayList<>();

    @OneToMany(mappedBy = "peca")
    private List<Producao> listaDePecasEmProducao = new ArrayList<>();

    /**
     * Construtor padrão da classe {@code Peca}.
     */
    public Peca(){}

    /**
     * Construtor completo, com todos os parâmetros para inicializar todos os atributos, a criação de objeto que representa o {@code Peca}.
     * @param   idPeca
     *          O ID da peça.
     * @param   nome
     *          O nome equivalente a uma categoria de classificação da peça.
     * @param   descricao
     *          A descrição da peça discorrendo a sua utilização.
     * @param   quantidadePecas
     *          A quantidade de peças utilizadas na montagem daquele chassis de veículo.
     * @param   listaDeVeiculosComEssaPeca
     *          A peça pode possuir uma lista de veículos, que pode ser checado o chassis de relacionamento em Veículos.
     * @param   listaDePecasEmEstoque
     *          A peça  pode possuir uma lista de manutenções, que pode ser checado o ID de relacionamento em Estoque.
     * @param   listaDePecasEmProducao
     *          A peça  pode possuir uma lista de manutenções, que pode ser checado o ID em Produção.
     *
     * @see Veiculo
     * @see Producao
     * @see Estoque
     */
    public Peca(Long idPeca, String nome, String descricao, int quantidadePecas, List<Veiculo> listaDeVeiculosComEssaPeca, List<Estoque> listaDePecasEmEstoque, List<Producao> listaDePecasEmProducao) {
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

    public List<Veiculo> getListaDeVeiculosComEssaPeca() {
        return listaDeVeiculosComEssaPeca;
    }

    public void setListaDeVeiculosComEssaPeca(List<Veiculo> listaDeVeiculosComEssaPeca) {
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
        return quantidadePecas == peca.quantidadePecas && idPeca.equals(peca.idPeca) && Objects.equals(nome, peca.nome) && Objects.equals(descricao, peca.descricao) && Objects.equals(listaDeVeiculosComEssaPeca, peca.listaDeVeiculosComEssaPeca) && Objects.equals(listaDePecasEmEstoque, peca.listaDePecasEmEstoque) && Objects.equals(listaDePecasEmProducao, peca.listaDePecasEmProducao);
    }

    @Override
    public int hashCode() {
        int result = idPeca.hashCode();
        result = 31 * result + Objects.hashCode(nome);
        result = 31 * result + Objects.hashCode(descricao);
        result = 31 * result + quantidadePecas;
        result = 31 * result + Objects.hashCode(listaDeVeiculosComEssaPeca);
        result = 31 * result + Objects.hashCode(listaDePecasEmEstoque);
        result = 31 * result + Objects.hashCode(listaDePecasEmProducao);
        return result;
    }

    /**
     * Exibe todas os atributos de peça
     * @return Retorna uma representação em string do objeto {@code Peca}.
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
