package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Representa o equipamento, o maquinário e as ferramentas
 * utilizadas na montagem do veículo.
 * Elas podem possuir manutenções associadas.
 *
 * <p>
 *  A entidade {@code Equipamento} está mapeada para a tabela "equipamentos" no banco de dados.
 *  </p>
 *
 * @see Manutencao
 *
 * @author Camila
 *
 * @since V1
 */
@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipamento;
    private String tipoEquipamento;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manutencao> listaDeManutencoes;

    /**
     * Construtor padrão da classe {@code Equipamento}.
     */
    public Equipamento() {
    }

    /**
     * Construtor completo, com todos os parâmetros para inicializar todos os atributos, a criação de objeto que representa o {@code Equipamento}.
     *
     * @param   idEquipamento
     *          O ID do equipamento.
     * @param   tipoEquipamento
     *          O tipo de equipamento, equivalente a uma categoria de classificação.
     * @param   descricao
     *          A descrição do equipamento discorrendo a sua utilização
     * @param   estado
     *          O status do equipamento que pode ser encontrado em: Estado.
     * @param   listaDeManutencoes
     *          O equipamento pode possuir uma lista de manutenções, que pode ser checado o ID de relacionamento em Manutenções.
     *
     * @see Estado
     * @see Manutencao
     */
    public Equipamento(Long idEquipamento, String tipoEquipamento, String descricao, Estado estado, List<Manutencao> listaDeManutencoes) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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
        if (estado != that.estado) return false;
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

    /**
     * Exibe todas os atributos de equipamento
     * @return Retorna uma representação em string do objeto {@code Equipamento}.
     */
    @Override
    public String toString() {
        return "EQUIPAMENTO [" +
                "ID equipamento: " + idEquipamento +
                ", Tipo de equipamento:" + tipoEquipamento +
                ", Descrição do equipamento: " + descricao +
                ", Estado do equipamento: " + estado +
                ", Lista de manutenções: " + listaDeManutencoes +
                ']';
    }
}
