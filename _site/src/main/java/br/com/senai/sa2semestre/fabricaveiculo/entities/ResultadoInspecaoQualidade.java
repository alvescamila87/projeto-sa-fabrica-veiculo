package br.com.senai.sa2semestre.fabricaveiculo.entities;

/**
 * Representa a situação como sendo de resultado da inspeção de qualidade após o processo de produção (linha de montagem) do veículo.
 * <p>
 *  Não é uma entidade, mas sim é um atributo relacionado à tabela {@code InspecaoQualidade} no banco de dados.
 *  </p>
 *
 * @see InspecaoQualidade
 *
 * @author Camila
 *
 * @since V1
 */
public enum ResultadoInspecaoQualidade {

    PENDENTE_INSPECAO,
    EM_ANDAMENTO,
    CONFORME,
    NAO_CONFORME,
    CANCELADO

}
