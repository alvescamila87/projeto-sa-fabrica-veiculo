package br.com.senai.sa2semestre.fabricaveiculo.entities;

/**
 * Representa o estado, vulgo "status", lista de estados para determinar o andamento do processo em si das classes: Manutenção, Equipamento e Produção.
 *
 * <p>
 *  Não é uma entidade, mas sim é um atributo relacionado às tabelas {@code Manutencao, Equipamento, Producao} no banco de dados.
 *  </p>
 *
 * @see Manutencao
 * @see Equipamento
 * @see Producao
 *
 * @author Camila
 *
 * @since V1
 */
public enum Estado {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDO,
    CANCELADO
}
