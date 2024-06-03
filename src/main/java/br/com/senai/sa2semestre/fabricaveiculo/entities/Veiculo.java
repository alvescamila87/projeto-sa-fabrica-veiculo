package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

/**
 * Representa o veículo
 */
@Entity
public class Veiculo {


    @Id
    private String chassis; // "chassis": "9hj NFYTf5 Kg VF2808": deve ser informado
    private String modelo;
    private int anoFabricacao;
    private String cor;
    @OneToMany(mappedBy = "veiculo_peca")
    private List<VeiculoPeca> listaDeVeiculosPecas;

    public Veiculo(){}

    public Veiculo(String chassis, String modelo, int anoFabricacao, String cor, List<VeiculoPeca> listaDeVeiculosPecas) {
        this.chassis = chassis;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.listaDeVeiculosPecas = listaDeVeiculosPecas;
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

    public List<VeiculoPeca> getListaDeVeiculosPecas() {
        return listaDeVeiculosPecas;
    }

    public void setListaDeVeiculosPecas(List<VeiculoPeca> listaDeVeiculosPecas) {
        this.listaDeVeiculosPecas = listaDeVeiculosPecas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veiculo veiculo = (Veiculo) o;

        if (anoFabricacao != veiculo.anoFabricacao) return false;
        if (!chassis.equals(veiculo.chassis)) return false;
        if (!Objects.equals(modelo, veiculo.modelo)) return false;
        if (!Objects.equals(cor, veiculo.cor)) return false;
        return Objects.equals(listaDeVeiculosPecas, veiculo.listaDeVeiculosPecas);
    }

    @Override
    public int hashCode() {
        int result = chassis.hashCode();
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + anoFabricacao;
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        result = 31 * result + (listaDeVeiculosPecas != null ? listaDeVeiculosPecas.hashCode() : 0);
        return result;
    }

    /**
     * Exibe todas os atributos de veículo
     * @return Retorna uma representação em string do objeto Veículo
     */
    @Override
    public String toString() {
        return "VEÍCULO [" +
                ", Chassis: " + chassis +
                ", Modelo: " + modelo +
                ", Ano de fabricação: " + anoFabricacao +
                ", Cor: " + cor +
                ", Lista de veículo peças: " + listaDeVeiculosPecas +
                ']';
    }
}
