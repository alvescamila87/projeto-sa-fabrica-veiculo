package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.InspecaoQualidade;
import br.com.senai.sa2semestre.fabricaveiculo.exceptions.NotFoundException;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.InspecaoQualidadeRepository;
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
@Tag(name = "Inspeções de Qualidade", description = "Endpoints para gerenciamento de inspeções de qualidade")
public class InspecaoQualidadeController {

    @Autowired
    private InspecaoQualidadeRepository inspecaoQualidadeRepository;

    /**
     * Obtém todas as inspeções de qualidade
     * @return uma lista de inspeções de qualidade
     */
    @Operation(summary = "Obtém todas as inspeções de qualidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inspeções de qualidade obtida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<InspecaoQualidade> getAllInspecoesQualidade(){
        return inspecaoQualidadeRepository.findAll();
    }

    /**
     * Obtém a inspeção de qualidade por ID fornecido
     * @param id a ser pesquisado
     * @return a inspeção de qualidade de acordo com o ID fornecedido
     * @throws NotFoundException se a inspeção de qualidade com o ID especificado não for encontrada.
     */
    @Operation(summary = "Obtém a inspeção de qualidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeção de qualidade encontrada"),
            @ApiResponse(responseCode = "404", description = "Inspeção de qualidade não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InspecaoQualidade> getInspecaoQualidadeById(@PathVariable Long id){
        Optional<InspecaoQualidade> inspecaoQualidadePesquisada = inspecaoQualidadeRepository.findById(id);

        if(inspecaoQualidadePesquisada.isPresent()){
            return ResponseEntity.ok(inspecaoQualidadePesquisada.get());
        } else {
            throw new NotFoundException("Inspeção de qualidade não encontrada com ID: " + id);
        }
    }

    /**
     * Cria uma nova inspeção de qualidade
     * @param inspecaoQualidade dados para registrar a inspeção de qualidade
     * @return a nova insperação de qualidade criada
     */
    @Operation(summary = "Cria uma nova inspeção de qualidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeção de qualidade criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public InspecaoQualidade createInspecaoQualidade(@RequestBody InspecaoQualidade inspecaoQualidade){
        return inspecaoQualidadeRepository.save(inspecaoQualidade);
    }

    /**
     * Atualiza os dados da inspeção de qualidade de acordo com o ID fornecido
     * @param id da inspeção de qualidade a ser pesquisada
     * @param inspecaoQualidadeParaAtualizar novo dados de atualização da inspeção de qualidade
     * @return a inspeção de qualidade atualizada
     * @throws NotFoundException se a inspeção de qualidade com o ID especificado não for encontrada.
     */
    @Operation(summary = "Atualiza os dados da inspeção de qualidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeção de qualidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inspeção de qualidade não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InspecaoQualidade> updateInspecaoQualidade(@PathVariable Long id, @RequestBody InspecaoQualidade inspecaoQualidadeParaAtualizar){
        Optional<InspecaoQualidade> inspecaoQualidadePesquisada = inspecaoQualidadeRepository.findById(id);

        if(inspecaoQualidadePesquisada.isPresent()){
            inspecaoQualidadeParaAtualizar.setIdInspecao(id);
            return ResponseEntity.ok(inspecaoQualidadeRepository.save(inspecaoQualidadeParaAtualizar));
        } else {
            throw new NotFoundException("Inspeção de qualidade não encontrada com ID: " + id);
        }
    }

    /**
     * Remove a inspeção de qualidade de acordo com o ID fornecido
     * @param id da inspeção de qualidade a ser pesquisada
     * @return mensagem falha ou sucesso da operação
     * @throws NotFoundException se a inspeção de qualidade com o ID especificado não for encontrada.
     */
    @Operation(summary = "Remove a inspeção de qualidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inspeção de qualidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inspeção de qualidade não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspecaoQualidade(@PathVariable Long id){
        Optional<InspecaoQualidade> inspecaoQualidadePesquisada = inspecaoQualidadeRepository.findById(id);

        if (inspecaoQualidadePesquisada.isPresent()){
            inspecaoQualidadeRepository.delete(inspecaoQualidadePesquisada.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new NotFoundException("Inspeção de qualidade não encontrada com ID: " + id);
        }
    }
}
