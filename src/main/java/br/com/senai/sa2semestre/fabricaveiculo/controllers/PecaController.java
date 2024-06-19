package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Peca;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de peças.
 *
 * <p>
 *     Este controlador possui os endpoints para: criar, atualizar, excluir e consultar informações de {@code Peca}.
 * </p>
 *
 * @see Peca
 * @see PecaRepository
 *
 * @author Camila
 * @since V1
 */
@RestController
@RequestMapping("/pecas")
public class PecaController {

    @Autowired
    private PecaRepository pecaRepository;

    /**
     * Obtém todos as peças cadastradas
     * @return uma lista de peças
     */
    @GetMapping
    public List<Peca> getAllPecas(){
        return pecaRepository.findAll();
    }

    /**
     * Obtém uma peça pelo ID
     * @param id da peça
     * @return a peça do ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Peca> getPecaById(@PathVariable Long id){
        Optional<Peca> pecaPesquisada = pecaRepository.findById(id);

        if(pecaPesquisada.isPresent()){
            return ResponseEntity.ok(pecaPesquisada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria uma nova peça
     * @param peca dados da nova peça
     * @return a peça criada
     */
    @PostMapping
    public Peca createPeca(@RequestBody Peca peca){
        return pecaRepository.save(peca);
    }

    /**
     * Atualiza os dados de uma peça existente
     * @param id da peça a ser atualizada
     * @param pecaParaAtualizar são os novos dados a serem atualizados na peça
     * @return a peça com os dados atualizados
     */
    @PutMapping("/{id}")
    public ResponseEntity<Peca> updatePeca(@PathVariable Long id, @RequestBody Peca pecaParaAtualizar){
        Optional<Peca> pecaPesquisada = pecaRepository.findById(id);

        if(pecaPesquisada.isPresent()){
            pecaParaAtualizar.setIdPeca(id);
            return ResponseEntity.ok(pecaRepository.save(pecaParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Excluir peça por id fornecida
     * @param id da peça a ser excluída
     * @return uma resposta indicando o sucesso ou falha da operação.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeca(@PathVariable Long id){
        Optional<Peca> pecaPesquisada = pecaRepository.findById(id);

        if(pecaPesquisada.isPresent()){
            pecaRepository.delete(pecaPesquisada.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
