package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VeiculoPecaId implements Serializable {

    private String chassis;
    private Long idPeca;

    public VeiculoPecaId(){}

    public VeiculoPecaId(String chassis, Long idPeca) {
        this.chassis = chassis;
        this.idPeca = idPeca;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeiculoPecaId that = (VeiculoPecaId) o;

        return Objects.equals(chassis, that.chassis) && Objects.equals(idPeca, that.idPeca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chassis, idPeca);
    }
}
