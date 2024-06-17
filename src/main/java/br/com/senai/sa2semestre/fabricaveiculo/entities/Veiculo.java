package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa o veículo a ser montado, ou seja,
 * o produto final da linha de montagem.
 */
@Entity
public class Veiculo {

    @Id
    private String chassis;
    private String modelo;
    private int anoFabricacao;
    private String cor;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "veiculo_peca",
            joinColumns = {@JoinColumn(name = "chassis")},
            inverseJoinColumns = {@JoinColumn(name = "idPeca")}

    )
    private List<Peca> listaDePecasUtilizadas = new ArrayList<>();

    public Veiculo(){}

    public Veiculo(String chassis, String modelo, int anoFabricacao, String cor, List<Peca> listaDePecasUtilizadas) {
        this.chassis = chassis;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.listaDePecasUtilizadas = listaDePecasUtilizadas;
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

    public List<Peca> getListaDePecasUtilizadas() {
        return listaDePecasUtilizadas;
    }

    public void setListaDePecasUtilizadas(List<Peca> listaDePecasUtilizadas) {
        this.listaDePecasUtilizadas = listaDePecasUtilizadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veiculo veiculo = (Veiculo) o;
        return anoFabricacao == veiculo.anoFabricacao && chassis.equals(veiculo.chassis) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(cor, veiculo.cor) && Objects.equals(listaDePecasUtilizadas, veiculo.listaDePecasUtilizadas);
    }

    @Override
    public int hashCode() {
        int result = chassis.hashCode();
        result = 31 * result + Objects.hashCode(modelo);
        result = 31 * result + anoFabricacao;
        result = 31 * result + Objects.hashCode(cor);
        result = 31 * result + Objects.hashCode(listaDePecasUtilizadas);
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
                ", Lista de peças utilizadas: " + listaDePecasUtilizadas +
                ']';
    }
}
