package br.com.senai.sa2semestre.fabricaveiculo.repositories;

import br.com.senai.sa2semestre.fabricaveiculo.entities.VeiculoPeca;
import br.com.senai.sa2semestre.fabricaveiculo.entities.VeiculoPecaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoPecasRepository extends JpaRepository<VeiculoPeca, VeiculoPecaId> {
}
