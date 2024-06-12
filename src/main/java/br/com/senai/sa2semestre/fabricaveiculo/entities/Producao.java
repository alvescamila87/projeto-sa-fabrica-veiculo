package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representa a produção de montagem de veículos
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

    public Producao(){}

    public Producao(Long idProducao, LocalDateTime dataHora, int quantidadeProduzida, Estado estado, Peca peca) {
        this.idProducao = idProducao;
        this.dataHora = dataHora;
        this.quantidadeProduzida = quantidadeProduzida;
        this.estado = estado;
        this.peca = peca;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producao producao = (Producao) o;

        if (quantidadeProduzida != producao.quantidadeProduzida) return false;
        if (!idProducao.equals(producao.idProducao)) return false;
        if (!Objects.equals(dataHora, producao.dataHora)) return false;
        if (estado != producao.estado) return false;
        return Objects.equals(peca, producao.peca);
    }

    @Override
    public int hashCode() {
        int result = idProducao.hashCode();
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0);
        result = 31 * result + quantidadeProduzida;
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (peca != null ? peca.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PRODUÇÃO [" +
                "ID Produção: " + idProducao +
                ", Produzido em: " + dataHora +
                ", Quantidade produzida: " + quantidadeProduzida +
                ", Estado: '" + estado + '\'' +
                ", Peça: " + peca +
                '}';
    }
}
