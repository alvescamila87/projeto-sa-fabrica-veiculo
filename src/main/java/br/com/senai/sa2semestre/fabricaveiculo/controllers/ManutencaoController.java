package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Manutencao;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de manutenção.
 */
@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    /**
     * Obtém todas as manutenções.
     * @return uma lista de manutenções.
     */
    @GetMapping
    public List<Manutencao> getAllManutencoes(){
        return manutencaoRepository.findAll();
    }

    /**
     * Obtém uma manutenção por ID.
     * @param id o ID da manutenção.
     * @return a manutenção com o ID especificado.
     */
    @GetMapping("{id}")
    public ResponseEntity<Manutencao> getManutencaoById(@PathVariable Long id){
        Optional<Manutencao> manutencaoPesquisada = manutencaoRepository.findById(id);

        if(manutencaoPesquisada.isPresent()){
            return ResponseEntity.ok(manutencaoPesquisada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria uma nova manutenção.
     * @param manutencao dados da nova manutenção.
     * @return a nova manutenção criada.
     */
    @PostMapping
    public Manutencao createManutencao(@RequestBody Manutencao manutencao){
        return manutencaoRepository.save(manutencao);
    }

    /**
     * Atualiza uma manutenção já existente.
     * @param id o ID da manutenção a ser atualizada.
     * @param manutencaoParaAtualizar os novos dados da manutenção.
     * @return a manutenção atualizada.
     */
    @PutMapping("{id}")
    public ResponseEntity<Manutencao> updateManutencao(@PathVariable Long id, Manutencao manutencaoParaAtualizar){
        Optional<Manutencao> manutencaoPesquisada = manutencaoRepository.findById(id);

        if(manutencaoPesquisada.isPresent()){
            manutencaoParaAtualizar.setIdManutencao(id);
            return ResponseEntity.ok(manutencaoRepository.save(manutencaoParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui uma manutenção por ID.
     * @param id o ID da manutenção a ser excluída.
     * @return uma resposta indicando o sucesso ou falha da operação.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteManutencao(@PathVariable Long id){
        Optional<Manutencao> manutencaoPesquisada = manutencaoRepository.findById(id);

        if(manutencaoPesquisada.isPresent()){
            manutencaoRepository.delete(manutencaoPesquisada.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
