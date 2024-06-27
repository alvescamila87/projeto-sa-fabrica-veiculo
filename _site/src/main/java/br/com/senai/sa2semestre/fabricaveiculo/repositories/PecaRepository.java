package br.com.senai.sa2semestre.fabricaveiculo.repositories;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {
}
