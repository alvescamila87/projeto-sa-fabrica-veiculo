package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Manutencao;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.ManutencaoRepository;
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
 * Controlador REST para gerenciamento de manutenção.
 *
 * <p>
 *     Este controlador possui os endpoints para: criar, atualizar, excluir e consultar informações de {@code Manutencao}.
 * </p>
 *
 * @see Manutencao
 * @see ManutencaoRepository
 *
 * @author Camila
 * @since V1
 */
@RestController
@RequestMapping("/manutencoes")
@Tag(name = "Manutenções", description = "Endpoints para gerenciamento de manutenções de equipamentos")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    /**
     * Obtém todas as manutenções.
     * @return uma lista de manutenções.
     */
    @Operation(summary = "Obtém todas as manutenções")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de manutenções encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Manutencao> getAllManutencoes(){
        return manutencaoRepository.findAll();
    }

    /**
     * Obtém uma manutenção por ID.
     * @param id o ID da manutenção.
     * @return a manutenção com o ID especificado.
     */
    @Operation(summary = "Obtém uma manutenção por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenção encontrada"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
    @Operation(summary = "Cria uma nova manutenção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenção criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
    @Operation(summary = "Atualiza uma manutenção existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenção atualizada"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Manutencao> updateManutencao(@PathVariable Long id, @RequestBody Manutencao manutencaoParaAtualizar){
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
    @Operation(summary = "Exclui uma manutenção por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Manutenção excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
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
