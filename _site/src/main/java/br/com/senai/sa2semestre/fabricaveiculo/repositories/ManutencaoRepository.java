package br.com.senai.sa2semestre.fabricaveiculo.repositories;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}
