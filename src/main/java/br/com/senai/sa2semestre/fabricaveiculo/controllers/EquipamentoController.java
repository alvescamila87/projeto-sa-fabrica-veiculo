package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Equipamento;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.EquipamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de equipamentos.
 *
 * <p>
 *     Este controlador possui os endpoints para: criar, atualizar, excluir e consultar informações de {@code Equipamento}.
 * </p>
 *
 * @see Equipamento
 * @see EquipamentoRepository
 *
 * @author Camila
 * @since V1
 */
@RestController
@RequestMapping("/equipamentos")
@Tag(name = "Equipamento", description = "Endpoints para gerenciamento de equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    /**
     * Obtém todos os equipamentos.
     * @return uma lista de equipamentos.
     */
    @Operation(summary = "Obtém todos os equipamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamentos encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipamento.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
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
    @Operation(summary = "Obtém um equipamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipamento.class))),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
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
    @Operation(summary = "Cria um novo equipamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipamento.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
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
    @Operation(summary = "Atualiza um equipamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipamento atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipamento.class))),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
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
    @Operation(summary = "Exclui um equipamento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Equipamento excluído com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
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
