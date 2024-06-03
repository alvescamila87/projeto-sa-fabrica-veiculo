package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VeiculoPeca {
    @ManyToOne
    @JoinColumn(name = "chassis", referencedColumnName = "chassis")
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name = "idPeca", referencedColumnName = "idPeca")
    private Peca peca;
    private int quantidadePecas;

    public VeiculoPeca(){}

    public VeiculoPeca(Veiculo veiculo, Peca peca, int quantidadePecas) {
        this.veiculo = veiculo;
        this.peca = peca;
        this.quantidadePecas = quantidadePecas;
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

    public int getQuantidadePecas() {
        return quantidadePecas;
    }

    public void setQuantidadePecas(int quantidadePecas) {
        this.quantidadePecas = quantidadePecas;
    }

    // Está considerando as duas chaves estrangeiras como comparação: id peça e chassis veículo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeiculoPeca that = (VeiculoPeca) o;

        if (quantidadePecas != that.quantidadePecas) return false;
        if (!veiculo.equals(that.veiculo)) return false;
        return peca.equals(that.peca);
    }

    @Override
    public int hashCode() {
        int result = veiculo.hashCode();
        result = 31 * result + peca.hashCode();
        result = 31 * result + quantidadePecas;
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
                ", Peça: " + peca +
                ", Quantidade de peças utilizadas: " + quantidadePecas +
                '}';
    }
}