package br.com.senai.sa2semestre.fabricaveiculo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
            name = "veiculo_peca",
            joinColumns = {@JoinColumn(name = "chassis")},
            inverseJoinColumns = {@JoinColumn(name = "idPeca")}

    )
    @JsonManagedReference
    @JsonIgnore
    private List<Peca> listaDePecasUtilizadas = new ArrayList<>();

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
     * @param   listaDePecasUtilizadas
     *          O veículo pode possuir uma lista de peças, que pode ser checado o ID de relacionamento em Peças.
     *
     * @see Peca
     */
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
     * @return Retorna uma representação em string do objeto {@code Veiculo}.
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
