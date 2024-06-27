package br.com.senai.sa2semestre.fabricaveiculo.repositories;

import br.com.senai.sa2semestre.fabricaveiculo.entities.InspecaoQualidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspecaoQualidadeRepository extends JpaRepository<InspecaoQualidade, Long> {
}
