package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.Enumerated;

/**
 * Representa o perfil de acesso como controle de usuário na aplicação
 * <p>
 *  Não é uma entidade, mas sim é um atributo relacionado à tabela {@code Usuario} no banco de dados.
 *  </p>
 *
 * @see Usuario

 * @author Camila
 *
 * @since V1
 */
public enum Perfil {

    ADMIN,
    SUPERVISOR,
    OPERADOR
}
