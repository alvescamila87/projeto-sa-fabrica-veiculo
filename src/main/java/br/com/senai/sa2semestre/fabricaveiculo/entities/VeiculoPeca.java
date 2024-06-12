package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

/**
 * Representa uma associação de veículo e peças
 */
@Entity
@Table(name = "veiculoPeca")
@IdClass(VeiculoPecaId.class)
public class VeiculoPeca {

    @Id
    @ManyToOne
    @JoinColumn(name = "chassis", referencedColumnName = "chassis")
    private Veiculo veiculo;

    @Id
    @ManyToOne
    @JoinColumn(name = "idPeca", referencedColumnName = "idPeca")
    private Peca peca;

    public VeiculoPeca(){}

    public VeiculoPeca(Veiculo veiculo, Peca peca) {
        this.veiculo = veiculo;
        this.peca = peca;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }


    // Está considerando as duas chaves estrangeiras como comparação: id peça e chassis veículo

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeiculoPeca that = (VeiculoPeca) o;

        if (!veiculo.equals(that.veiculo)) return false;
        return peca.equals(that.peca);
    }

    @Override
    public int hashCode() {
        int result = veiculo.hashCode();
        result = 31 * result + peca.hashCode();
        return result;
    }

    /**
     * Exibe todas os atributos de veículos peças
     * @return Retorna uma representação em string do objeto Veículos Peças
     */
    @Override
    public String toString() {
        return "VEÍCULOS PEÇAS [" +
                "Veículo: " + veiculo +
                ", ID Peça: " + peca +
                '}';
    }
}