package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representa a inspeção de qualidade que deve garantir
 * que o veículo finalizado atenda aos padrões de qualidade e segurança.
 */
@Entity
public class InspecaoQualidade {
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

    public InspecaoQualidade(){}

    public InspecaoQualidade(Long idInspecao, LocalDateTime dataHoraInspecao, ResultadoInspecaoQualidade resultadoInspecaoQualidade, String descricaoInspecaoQualidade, Producao producao) {
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

        InspecaoQualidade inspecaoQualidade = (InspecaoQualidade) o;

        if (!idInspecao.equals(inspecaoQualidade.idInspecao)) return false;
        if (!Objects.equals(dataHoraInspecao, inspecaoQualidade.dataHoraInspecao))
            return false;
        if (resultadoInspecaoQualidade != inspecaoQualidade.resultadoInspecaoQualidade) return false;
        if (!Objects.equals(descricaoInspecaoQualidade, inspecaoQualidade.descricaoInspecaoQualidade))
            return false;
        return Objects.equals(producao, inspecaoQualidade.producao);
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
                ", Inspecionado em: " + dataHoraInspecao +
                ", Resultado da inspeção: " + resultadoInspecaoQualidade +
                ", Descrição da inspeção: '" + descricaoInspecaoQualidade +
                ", ID Produção: " + producao +
                ']';
    }
}
