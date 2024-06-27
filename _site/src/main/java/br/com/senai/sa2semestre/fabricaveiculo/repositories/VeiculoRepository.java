package br.com.senai.sa2semestre.fabricaveiculo.repositories;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
}
