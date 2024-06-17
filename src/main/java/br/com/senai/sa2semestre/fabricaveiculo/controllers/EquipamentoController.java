package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Equipamento;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de equipamentos.
 */
@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    /**
     * Obtém todos os equipamentos.
     * @return uma lista de equipamentos.
     */
    @GetMapping
    public List<Equipamento> getAllEquipamentos(){
        return equipamentoRepository.findAll();
    }

    /*
    // GetById - Forma 1 - Professor

     @GetMapping("{id}")
     public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable Long id){
        Optional<Equipamento> equipamentoPesquisado = equipamentoRepository.findById(id);
        return equipamentoPesquisado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

     }
     */

    //GetById - Forma 2 - Camila
    /**
     * Obtém um equipamento por ID.
     * @param id o ID do equipamento.
     * @return o equipamento com o ID especificado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable Long id){
        Optional<Equipamento> equipamentoPesquisado = equipamentoRepository.findById(id);

        if(equipamentoPesquisado.isPresent()){
            return ResponseEntity.ok(equipamentoPesquisado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria um novo equipamento.
     * @param equipamento dados do novo equipamento.
     * @return o novo equipamento criado.
     */
    @PostMapping
    public Equipamento createEquipamento(@RequestBody Equipamento equipamento){
        return equipamentoRepository.save(equipamento);
    }

    /**
     * Atualiza um equipamento existente.
     * @param id o ID do equipamento a ser atualizado.
     * @param equipamentoParaAtualizar os novos dados do equipamento.
     * @return o equipamento atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> updateEquipamento(@PathVariable Long id, @RequestBody Equipamento equipamentoParaAtualizar) {
        Optional<Equipamento> equipamentoPesquisado = equipamentoRepository.findById(id);

        if (equipamentoPesquisado.isPresent()) {
            equipamentoParaAtualizar.setIdEquipamento(id);
            return ResponseEntity.ok(equipamentoRepository.save(equipamentoParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui um equipamento por ID.
     * @param id o ID do equipamento a ser excluído.
     * @return uma resposta indicando o sucesso ou falha da operação.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Long id){
        Optional<Equipamento> equipamentoPesquisado = equipamentoRepository.findById(id);

        if(equipamentoPesquisado.isPresent()){
            equipamentoRepository.delete(equipamentoPesquisado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
