package br.com.senai.sa2semestre.fabricaveiculo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representa a manutenção e reparo dos equipamentos ao
 * longo do processo de produção de montagem do veículo.
 *
 * <p>
 *  A entidade {@code Manutencao} está mapeada para a tabela "manutencoes" no banco de dados.
 *  </p>
 *
 * @see Equipamento
 *
 * @author Camila
 *
 * @since V1
 */

@Entity
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManutencao;

    @ManyToOne
    @JoinColumn(name = "idEquipamento", referencedColumnName = "idEquipamento")
    @JsonBackReference
    private Equipamento equipamento;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String descricaoServico;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    /**
     * Construtor padrão da classe {@code Manutencao}.
     */
    public Manutencao(){

    }

    /**
     * Construtor completo, com todos os parâmetros para inicializar todos os atributos, a criação de objeto que representa o {@code Manutencao}.
     *
     * @param   idManutencao
     *          O ID da manutenção.
     * @param   equipamento
     *          O equipamento relacionado à manutenção.
     * @param   dataHoraInicio
     *          A data e a hora de início da manutenção do equipamento.
     * @param   dataHoraFim
     *          A data e a hora de fim da manutenção do equipamento.
     * @param   descricaoServico
     *          A descrição do serviço de manutenção realizado no equipamento.
     * @param   estado
     *          O status da manutenção que pode ser encontrado em: Estado.
     *
     * @see Estado
     * @see Equipamento
     */
    public Manutencao(Long idManutencao, Equipamento equipamento, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoServico, Estado estado) {
        this.idManutencao = idManutencao;
        this.equipamento = equipamento;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manutencao that = (Manutencao) o;

        if (!idManutencao.equals(that.idManutencao)) return false;
        if (!Objects.equals(equipamento, that.equipamento)) return false;
        if (!Objects.equals(dataHoraInicio, that.dataHoraInicio))
            return false;
        if (!Objects.equals(dataHoraFim, that.dataHoraFim)) return false;
        if (!Objects.equals(descricaoServico, that.descricaoServico))
            return false;
        return estado == that.estado;
    }

    @Override
    public int hashCode() {
        int result = idManutencao.hashCode();
        result = 31 * result + (equipamento != null ? equipamento.hashCode() : 0);
        result = 31 * result + (dataHoraInicio != null ? dataHoraInicio.hashCode() : 0);
        result = 31 * result + (dataHoraFim != null ? dataHoraFim.hashCode() : 0);
        result = 31 * result + (descricaoServico != null ? descricaoServico.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    /**
     * Exibe todas os atributos de manutenção
     * @return Retorna uma representação em string do objeto {@code Manutencao}.
     */
    @Override
    public String toString() {
        return "MANUTENÇÃO [" +
                "ID Manutenção: " + idManutencao +
                ", ID Equipamento: " + equipamento +
                ", Data início: " + dataHoraInicio +
                ", Data fim: " + dataHoraFim +
                ", Descrição serviço: " + descricaoServico +
                ", Estado da manutenção: " + estado +
                ']';
    }
}
