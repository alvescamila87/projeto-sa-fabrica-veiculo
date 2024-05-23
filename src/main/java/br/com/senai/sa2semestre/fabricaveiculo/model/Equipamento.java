package br.com.senai.sa2semestre.fabricaveiculo.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipamento;
    private String tipoEquipamento;
    private String descricao;
    private String estado;

    @OneToMany(mappedBy = "equipamento")
    private List<Manutencao> listaDeManutencoes;

    public Equipamento() {
    }

    public Equipamento(Long idEquipamento, String tipoEquipamento, String descricao, String estado, List<Manutencao> listaDeManutencoes) {
        this.idEquipamento = idEquipamento;
        this.tipoEquipamento = tipoEquipamento;
        this.descricao = descricao;
        this.estado = estado;
        this.listaDeManutencoes = listaDeManutencoes;
    }

    public Long getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Long idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Manutencao> getListaDeManutencoes() {
        return listaDeManutencoes;
    }

    public void setListaDeManutencoes(List<Manutencao> listaDeManutencoes) {
        this.listaDeManutencoes = listaDeManutencoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipamento that = (Equipamento) o;

        if (!idEquipamento.equals(that.idEquipamento)) return false;
        if (!Objects.equals(tipoEquipamento, that.tipoEquipamento))
            return false;
        if (!Objects.equals(descricao, that.descricao)) return false;
        if (!Objects.equals(estado, that.estado)) return false;
        return Objects.equals(listaDeManutencoes, that.listaDeManutencoes);
    }

    @Override
    public int hashCode() {
        int result = idEquipamento.hashCode();
        result = 31 * result + (tipoEquipamento != null ? tipoEquipamento.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (listaDeManutencoes != null ? listaDeManutencoes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Equipamento [" +
                "ID equipamento: " + idEquipamento +
                ", Tipo de equipamento:" + tipoEquipamento +
                ", Descrição do equipamento: " + descricao +
                ", Estado do equipamento: " + estado +
                ", Lista de manutenções: " + listaDeManutencoes +
                ']';
    }
}
