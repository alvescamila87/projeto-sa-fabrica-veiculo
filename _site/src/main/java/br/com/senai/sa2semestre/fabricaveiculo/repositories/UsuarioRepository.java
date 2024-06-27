package br.com.senai.sa2semestre.fabricaveiculo.repositories;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
