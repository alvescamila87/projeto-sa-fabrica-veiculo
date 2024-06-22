package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Peca;
import br.com.senai.sa2semestre.fabricaveiculo.exceptions.NotFoundException;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.PecaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Peças", description = "Endpoints para gerenciamento de peças")
public class PecaController {

    @Autowired
    private PecaRepository pecaRepository;

    /**
     * Obtém todos as peças cadastradas
     * @return uma lista de peças
     */
    @Operation(summary = "Obtém todas as peças cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de peças encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Peca> getAllPecas(){
        return pecaRepository.findAll();
    }

    /**
     * Obtém uma peça pelo ID
     * @param id da peça
     * @return a peça do ID fornecido
     * @throws NotFoundException se a peça com o ID especificado não for encontrada.
     */
    @Operation(summary = "Obtém uma peça por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peça encontrada"),
            @ApiResponse(responseCode = "404", description = "Peça não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Peca> getPecaById(@PathVariable Long id){
        Optional<Peca> pecaPesquisada = pecaRepository.findById(id);

        if(pecaPesquisada.isPresent()){
            return ResponseEntity.ok(pecaPesquisada.get());
        } else {
            throw new NotFoundException("Peça não encontrada com ID: " + id);
        }
    }

    /**
     * Cria uma nova peça
     * @param peca dados da nova peça
     * @return a peça criada
     */
    @Operation(summary = "Cria uma nova peça")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peça criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public Peca createPeca(@RequestBody Peca peca){
        return pecaRepository.save(peca);
    }

    /**
     * Atualiza os dados de uma peça existente
     * @param id da peça a ser atualizada
     * @param pecaParaAtualizar são os novos dados a serem atualizados na peça
     * @return a peça com os dados atualizados
     * @throws NotFoundException se a peça com o ID especificado não for encontrada.
     */
    @Operation(summary = "Atualiza uma peça existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peça atualizada"),
            @ApiResponse(responseCode = "404", description = "Peça não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Peca> updatePeca(@PathVariable Long id, @RequestBody Peca pecaParaAtualizar){
        Optional<Peca> pecaPesquisada = pecaRepository.findById(id);

        if(pecaPesquisada.isPresent()){
            pecaParaAtualizar.setIdPeca(id);
            return ResponseEntity.ok(pecaRepository.save(pecaParaAtualizar));
        } else {
            throw new NotFoundException("Peça não encontrada com ID: " + id);
        }
    }

    /**
     * Excluir peça por id fornecida
     * @param id da peça a ser excluída
     * @return uma resposta indicando o sucesso ou falha da operação.
     * @throws NotFoundException se a peça com o ID especificado não for encontrada.
     */
    @Operation(summary = "Exclui uma peça por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Peça excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Peça não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeca(@PathVariable Long id){
        Optional<Peca> pecaPesquisada = pecaRepository.findById(id);

        if(pecaPesquisada.isPresent()){
            pecaRepository.delete(pecaPesquisada.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new NotFoundException("Peça não encontrada com ID: " + id);
        }
    }
}
