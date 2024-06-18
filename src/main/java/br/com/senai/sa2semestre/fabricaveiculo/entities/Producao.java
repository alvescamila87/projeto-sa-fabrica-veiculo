package br.com.senai.sa2semestre.fabricaveiculo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa a produção, ou seja, o processo da linha de montagem do veículo,
 * utilizando peças, componentes. Possui inspeção de qualidade ao final do ciclo de produção.
 *
 * <p>
 *  A entidade {@code Producao} está mapeada para a tabela "producoes" no banco de dados.
 *  </p>
 *
 * @see InspecaoQualidade
 *
 * @author Camila
 *
 * @since V1
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
    @JsonBackReference
    private Peca peca;

    @OneToMany(mappedBy = "producao")
    private List<InspecaoQualidade> listaDeInspecoesDeQualidade = new ArrayList<>();

    /**
     * Construtor padrão da classe {@code Producao}.
     */
    public Producao(){}

    /**
     * Construtor completo, com todos os parâmetros para inicializar todos os atributos, a criação de objeto que representa o {@code Producao}.
     *
     * @param   idProducao
     *          O ID de produção
     * @param   dataHora
     *          A data e hora que ocorreu a produção (montagem do veículo).
     * @param   quantidadeProduzida
     *          Quantidade de montagens de veículos de chassis / modelos realizados.
     * @param   estado
     *          O status da produção que pode ser encontrado em: Estado.
     * @param   peca
     *          A peça relacionada à linha de produção do veículo.
     * @param   listaDeInspecoesDeQualidade
     *          A produção pode possuir uma lista de inspeções de qualidade, que pode ser checado o ID de relacionamento em Inspeções de qualidade.
     */
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

    /**
     * Exibe todas os atributos de equipamento
     * @return Retorna uma representação em string do objeto {@code Producao}.
     */
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
