package br.com.senai.sa2semestre.fabricaveiculo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa o veículo a ser montado com as peças de acordo com o chassis, ou seja, o produto final da fábrica na linha de produção.
 * <p>
 *  A entidade {@code Veiculo} está mapeada para a tabela "veiculos" no banco de dados.
 *  </p>
 *
 * @see Peca
 *
 * @author Camila
 *
 * @since V1
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
            name = "veiculos_pecas",
            joinColumns = {@JoinColumn(name = "chassis")},
            inverseJoinColumns = {@JoinColumn(name = "idPeca")}

    )
    @JsonIgnore
    private List<Peca> listaDePecasUtilizadasNoVeiculo = new ArrayList<>();

    /**
     * Construtor padrão da classe {@code Veiculo}.
     */
    public Veiculo(){}

    /**
     * Construtor completo, com todos os parâmetros para inicializar todos os atributos, a criação de objeto que representa o {@code Veiculo}.
     *
     * @param   chassis
     *          O chassis do veículo (código).
     * @param   modelo
     *          O modelo do veículo relacionado à marca da fábrica de montagem.
     * @param   anoFabricacao
     *          O ano de fabricação do veículo que será a de produção do veículo.
     * @param   cor
     *          A cor do veículo.
     * @param   listaDePecasUtilizadasNoVeiculo
     *          O veículo pode possuir uma lista de peças, que pode ser checado o ID de relacionamento em Peças.
     *
     * @see Peca
     */
    public Veiculo(String chassis, String modelo, int anoFabricacao, String cor, List<Peca> listaDePecasUtilizadasNoVeiculo) {
        this.chassis = chassis;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.listaDePecasUtilizadasNoVeiculo = listaDePecasUtilizadasNoVeiculo;
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

    public List<Peca> getListaDePecasUtilizadasNoVeiculo() {
        return listaDePecasUtilizadasNoVeiculo;
    }

    public void setListaDePecasUtilizadasNoVeiculo(List<Peca> listaDePecasUtilizadasNoVeiculo) {
        this.listaDePecasUtilizadasNoVeiculo = listaDePecasUtilizadasNoVeiculo;
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
        return Objects.equals(listaDePecasUtilizadasNoVeiculo, veiculo.listaDePecasUtilizadasNoVeiculo);
    }

    @Override
    public int hashCode() {
        int result = chassis.hashCode();
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + anoFabricacao;
        result = 31 * result + (cor != null ? cor.hashCode() : 0);
        result = 31 * result + (listaDePecasUtilizadasNoVeiculo != null ? listaDePecasUtilizadasNoVeiculo.hashCode() : 0);
        return result;
    }

    /**
     * Exibe todas os atributos de veículo
     * @return Retorna uma representação em string do objeto {@code Veiculo}.
     */
    @Override
    public String toString() {
        return "VEÍCULO [" +
                ", Chassis: " + chassis +
                ", Modelo: " + modelo +
                ", Ano de fabricação: " + anoFabricacao +
                ", Cor: " + cor +
                ", Lista de peças utilizadas no veículo: " + listaDePecasUtilizadasNoVeiculo +
                ']';
    }
}
