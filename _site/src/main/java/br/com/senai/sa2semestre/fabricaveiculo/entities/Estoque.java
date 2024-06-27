package br.com.senai.sa2semestre.fabricaveiculo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Representa o estoque de peças e
 * componentes necessários para a produção (montagem do veículo).
 *
 * <p>
 *  A entidade {@code Estoque} está mapeada para a tabela "estoques" no banco de dados.
 *  </p>
 *
 * @see Peca
 *
 * @author Camila
 *
 * @since V1
 */
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstoque;
    private int quantidadeDisponivel;

    @ManyToOne
    @JoinColumn(name = "idPeca", referencedColumnName = "idPeca")
    @JsonBackReference
    private Peca peca;

    /**
     * Construtor padrão da classe {@code Estoque}, sem parâmetros
     */
    public Estoque(){}

    /**
     * Construtor completo, com todos os parâmetros para inicializar todos os atributos, a criação de objeto que representa o {@code Estoque}.
     *
     * @param   idEstoque
     *          O ID do Estoque
     * @param   quantidadeDisponivel
     *          A do item em si (peça) disponível no estoque
     * @param   peca
     *          A peça relacionada ao estoque.
     *
     * @see Peca
     */
    public Estoque(Long idEstoque, int quantidadeDisponivel, Peca peca) {
        this.idEstoque = idEstoque;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.peca = peca;
    }

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estoque estoque = (Estoque) o;

        if (quantidadeDisponivel != estoque.quantidadeDisponivel) return false;
        if (!idEstoque.equals(estoque.idEstoque)) return false;
        return Objects.equals(peca, estoque.peca);
    }

    @Override
    public int hashCode() {
        int result = idEstoque.hashCode();
        result = 31 * result + quantidadeDisponivel;
        result = 31 * result + (peca != null ? peca.hashCode() : 0);
        return result;
    }

    /**
     * Exibe todas os atributos de estoque
     * @return Retorna uma representação em string do objeto {@code Estoque}.
     */
    @Override
    public String toString() {
        return "ESTOQUE [" +
                "ID Estoque: " + idEstoque +
                ", Quantidade de disponível: " + quantidadeDisponivel +
                ", ID Peça: " + peca +
                ']';
    }
}
