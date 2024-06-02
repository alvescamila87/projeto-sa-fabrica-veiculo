package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Veiculo {

    @Id
    private String chassis;
    private String modelo;
    private int anoFabricacao;
    private String cor;

    public Veiculo(){}

    public Veiculo(String chassis, String modelo, int anoFabricacao, String cor) {
        this.chassis = chassis;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veiculo veiculo = (Veiculo) o;

        if (anoFabricacao != veiculo.anoFabricacao) return false;
        if (!chassis.equals(veiculo.chassis)) return false;
        if (!Objects.equals(modelo, veiculo.modelo)) return false;
        return Objects.equals(cor, veiculo.cor);
    }

    @Override
    public int hashCode() {
        int result = chassis.hashCode();
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + anoFabricacao;
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Veículo [" +
                ", Chassis: " + chassis +
                ", Modelo: " + modelo +
                ", Ano de fabricação: " + anoFabricacao +
                ", Cor: " + cor +
                ']';
    }
}