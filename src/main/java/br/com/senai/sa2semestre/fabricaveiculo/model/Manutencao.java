package br.com.senai.sa2semestre.fabricaveiculo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManutencao;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String descricaoServico;
    private String estado;

    public Manutencao(){

    }

    public Manutencao(Long idManutencao, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoServico, String estado) {
        this.idManutencao = idManutencao;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.descricaoServico = descricaoServico;
        this.estado = estado;
    }

    public Long getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Long idManutencao) {
        this.idManutencao = idManutencao;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manutencao that = (Manutencao) o;

        if (!idManutencao.equals(that.idManutencao)) return false;
        if (!Objects.equals(dataHoraInicio, that.dataHoraInicio))
            return false;
        if (!Objects.equals(dataHoraFim, that.dataHoraFim)) return false;
        if (!Objects.equals(descricaoServico, that.descricaoServico))
            return false;
        return Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        int result = idManutencao.hashCode();
        result = 31 * result + (dataHoraInicio != null ? dataHoraInicio.hashCode() : 0);
        result = 31 * result + (dataHoraFim != null ? dataHoraFim.hashCode() : 0);
        result = 31 * result + (descricaoServico != null ? descricaoServico.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Manutenção [" +
                "ID Manutenção: " + idManutencao +
                ", Data início: " + dataHoraInicio +
                ", Data fim: " + dataHoraFim +
                ", Descrição serviço: " + descricaoServico +
                ", Estado da manutenção: " + estado +
                ']';
    }
}
