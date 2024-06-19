package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.InspecaoQualidade;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.InspecaoQualidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de inspeções de qualidade.
 *
 * <p>
 *     Este controlador possui os endpoints para: criar, atualizar, excluir e consultar informações de {@code InspecaoQualidade}.
 * </p>
 *
 * @see InspecaoQualidade
 * @see InspecaoQualidadeRepository
 *
 * @author Camila
 * @since V1
 */
@RestController
@RequestMapping("/inspecoes")
public class InspecaoQualidadeController {

    @Autowired
    private InspecaoQualidadeRepository inspecaoQualidadeRepository;

    /**
     * Obtém todas as inspeções de qualidade
     * @return uma lista de inspeções de qualidade
     */
    @GetMapping
    public List<InspecaoQualidade> getAllInspecoesQualidade(){
        return inspecaoQualidadeRepository.findAll();
    }

    /**
     * Obtém a inspeção de qualidade por ID fornecido
     * @param id a ser pesquisado
     * @return a inspeção de qualidade de acordo com o ID fornecedido
     */
    @GetMapping("/{id}")
    public ResponseEntity<InspecaoQualidade> getInspecaoQualidadeById(@PathVariable Long id){
        Optional<InspecaoQualidade> inspecaoQualidadePesquisada = inspecaoQualidadeRepository.findById(id);

        if(inspecaoQualidadePesquisada.isPresent()){
            return ResponseEntity.ok(inspecaoQualidadePesquisada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria uma nova inspeção de qualidade
     * @param inspecaoQualidade dados para registrar a inspeção de qualidade
     * @return a nova insperação de qualidade criada
     */
    @PostMapping
    public InspecaoQualidade createInspecaoQualidade(@RequestBody InspecaoQualidade inspecaoQualidade){
        return inspecaoQualidadeRepository.save(inspecaoQualidade);
    }

    /**
     * Atualiza os dados da inspeção de qualidade de acordo com o ID fornecido
     * @param id da inspeção de qualidade a ser pesquisada
     * @param inspecaoQualidadeParaAtualizar novo dados de atualização da inspeção de qualidade
     * @return a inspeção de qualidade atualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<InspecaoQualidade> updateInspecaoQualidade(@PathVariable Long id, @RequestBody InspecaoQualidade inspecaoQualidadeParaAtualizar){
        Optional<InspecaoQualidade> inspecaoQualidadePesquisada = inspecaoQualidadeRepository.findById(id);

        if(inspecaoQualidadePesquisada.isPresent()){
            inspecaoQualidadeParaAtualizar.setIdInspecao(id);
            return ResponseEntity.ok(inspecaoQualidadeRepository.save(inspecaoQualidadeParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Remove a inspeção de qualidade de acordo com o ID fornecido
     * @param id da inspeção de qualidade a ser pesquisada
     * @return mensagem falha ou sucesso da operação
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspecaoQualidade(@PathVariable Long id){
        Optional<InspecaoQualidade> inspecaoQualidadePesquisada = inspecaoQualidadeRepository.findById(id);

        if (inspecaoQualidadePesquisada.isPresent()){
            inspecaoQualidadeRepository.delete(inspecaoQualidadePesquisada.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
