package br.com.senai.sa2semestre.fabricaveiculo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa a produção, ou seja, o processo de montagem do veículo,
 * utilizando peças e equipamentos.
 */
@Entity
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducao;
    private LocalDateTime dataHora;
    private int quantidadeProduzida;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "idPeca", referencedColumnName = "idPeca")
    private Peca peca;

    @OneToMany(mappedBy = "producao")
    private List<InspecaoQualidade> listaDeInspecoesDeQualidade = new ArrayList<>();


    public Producao(){}

    public Producao(Long idProducao, LocalDateTime dataHora, int quantidadeProduzida, Estado estado, Peca peca, List<InspecaoQualidade> listaDeInspecoesDeQualidade) {
        this.idProducao = idProducao;
        this.dataHora = dataHora;
        this.quantidadeProduzida = quantidadeProduzida;
        this.estado = estado;
        this.peca = peca;
        this.listaDeInspecoesDeQualidade = listaDeInspecoesDeQualidade;
    }

    public Long getIdProducao() {
        return idProducao;
    }

    public void setIdProducao(Long idProducao) {
        this.idProducao = idProducao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(int quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public List<InspecaoQualidade> getListaDeInspecoesDeQualidade() {
        return listaDeInspecoesDeQualidade;
    }

    public void setListaDeInspecoesDeQualidade(List<InspecaoQualidade> listaDeInspecoesDeInspecaoQualidade) {
        this.listaDeInspecoesDeQualidade = listaDeInspecoesDeInspecaoQualidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producao producao = (Producao) o;

        if (quantidadeProduzida != producao.quantidadeProduzida) return false;
        if (!idProducao.equals(producao.idProducao)) return false;
        if (!Objects.equals(dataHora, producao.dataHora)) return false;
        if (estado != producao.estado) return false;
        if (!Objects.equals(peca, producao.peca)) return false;
        return Objects.equals(listaDeInspecoesDeQualidade, producao.listaDeInspecoesDeQualidade);
    }

    @Override
    public int hashCode() {
        int result = idProducao.hashCode();
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0);
        result = 31 * result + quantidadeProduzida;
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (peca != null ? peca.hashCode() : 0);
        result = 31 * result + (listaDeInspecoesDeQualidade != null ? listaDeInspecoesDeQualidade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PRODUÇÃO [" +
                "ID Produção: " + idProducao +
                ", Produzido em: " + dataHora +
                ", Quantidade produzida: " + quantidadeProduzida +
                ", Estado: " + estado +
                ", ID Peça: " + peca +
                ", Lista de inspeções de qualidade: " + listaDeInspecoesDeQualidade +
                ']';
    }
}
