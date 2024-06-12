package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representa a insperação de qualidade após durante o processo de montagem do veículo
 */
@Entity
public class Qualidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInspecao;
    private LocalDateTime dataHoraInspecao;
    @Enumerated(EnumType.STRING)
    private ResultadoInspecaoQualidade resultadoInspecaoQualidade;
    private String descricaoInspecaoQualidade;
    @ManyToOne
    @JoinColumn(name = "idProducao", referencedColumnName = "idProducao")
    private Producao producao;

    public Qualidade(){}

    public Qualidade(Long idInspecao, LocalDateTime dataHoraInspecao, ResultadoInspecaoQualidade resultadoInspecaoQualidade, String descricaoInspecaoQualidade, Producao producao) {
        this.idInspecao = idInspecao;
        this.dataHoraInspecao = dataHoraInspecao;
        this.resultadoInspecaoQualidade = resultadoInspecaoQualidade;
        this.descricaoInspecaoQualidade = descricaoInspecaoQualidade;
        this.producao = producao;
    }

    public Long getIdInspecao() {
        return idInspecao;
    }

    public void setIdInspecao(Long idInspecao) {
        this.idInspecao = idInspecao;
    }

    public LocalDateTime getDataHoraInspecao() {
        return dataHoraInspecao;
    }

    public void setDataHoraInspecao(LocalDateTime dataHoraInspecao) {
        this.dataHoraInspecao = dataHoraInspecao;
    }

    public ResultadoInspecaoQualidade getResultadoInspecaoQualidade() {
        return resultadoInspecaoQualidade;
    }

    public void setResultadoInspecaoQualidade(ResultadoInspecaoQualidade resultadoInspecaoQualidade) {
        this.resultadoInspecaoQualidade = resultadoInspecaoQualidade;
    }

    public String getDescricaoInspecaoQualidade() {
        return descricaoInspecaoQualidade;
    }

    public void setDescricaoInspecaoQualidade(String descricaoInspecaoQualidade) {
        this.descricaoInspecaoQualidade = descricaoInspecaoQualidade;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Qualidade qualidade = (Qualidade) o;

        if (!idInspecao.equals(qualidade.idInspecao)) return false;
        if (!Objects.equals(dataHoraInspecao, qualidade.dataHoraInspecao))
            return false;
        if (resultadoInspecaoQualidade != qualidade.resultadoInspecaoQualidade) return false;
        if (!Objects.equals(descricaoInspecaoQualidade, qualidade.descricaoInspecaoQualidade))
            return false;
        return Objects.equals(producao, qualidade.producao);
    }

    @Override
    public int hashCode() {
        int result = idInspecao.hashCode();
        result = 31 * result + (dataHoraInspecao != null ? dataHoraInspecao.hashCode() : 0);
        result = 31 * result + (resultadoInspecaoQualidade != null ? resultadoInspecaoQualidade.hashCode() : 0);
        result = 31 * result + (descricaoInspecaoQualidade != null ? descricaoInspecaoQualidade.hashCode() : 0);
        result = 31 * result + (producao != null ? producao.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "INSPEÇÃO DE QUALIDADE [" +
                "ID Inspeção: " + idInspecao +
                ", Data e hora da inspeção: " + dataHoraInspecao +
                ", Resultado da inspeção: " + resultadoInspecaoQualidade +
                ", Descrição da inspeção: '" + descricaoInspecaoQualidade + '\'' +
                ", ID Produção: " + producao +
                ']';
    }
}
