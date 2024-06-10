package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.Enumerated;

/*
 * ENUMs são tipos de dados simples que não são mapeados diretamente como entidades no banco de dados.
 */
public enum Perfil {

    ADMIN,
    SUPERVISOR,
    OPERADOR
}
