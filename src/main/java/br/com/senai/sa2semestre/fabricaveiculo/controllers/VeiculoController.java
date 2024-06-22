package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Veiculo;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.VeiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de veículos.
 *
 * <p>
 *     Este controlador possui os endpoints para: criar, atualizar, excluir e consultar informações de {@code Veiculo}.
 * </p>
 *
 * @see Veiculo
 * @see VeiculoRepository
 *
 * @author Camila
 * @since V1
 */
@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículos", description = "Endpoints para gerenciamento de veículo")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    /**
     * Obtém todos os veículos
     * @return uma lista de veículos cadastrados.
     */
    @Operation(summary = "Obtém todos os veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veículos obtida com sucesso", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping
    public List<Veiculo> getAllVeiculos(){
        return veiculoRepository.findAll();
    }

    /**
     * Obtém um veículo por chassis (que representa o ID)
     * @param chassis é o chassis do veículo
     * @return o veículo com o chassis especificado.
     */
    @Operation(summary = "Obtém o veículo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{chassis}")
    public ResponseEntity<Veiculo> getVeiculoByChassis(@PathVariable String chassis){
        Optional<Veiculo> veiculoPesquisado = veiculoRepository.findById(chassis);

        if(veiculoPesquisado.isPresent()){
            return ResponseEntity.ok(veiculoPesquisado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria um novo veículo
     * @param veiculo dados do novo veículo
     * @return o novo veículo criado.
     */
    @Operation(summary = "Cria um novo veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public Veiculo createVeiculo(@RequestBody Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    /**
     * Atualiza um veículo existente
     * @param chassis o chassis do veículo a ser atualizado
     * @param veiculoParaAtualizar os novos dados do veículo
     * @return o veículo atualizado
     */
    @Operation(summary = "Atualiza as informações do veículo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{chassis}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable String chassis, @RequestBody Veiculo veiculoParaAtualizar){
        Optional<Veiculo> veiculoPesquisado = veiculoRepository.findById(chassis);

        if(veiculoPesquisado.isPresent()){
            veiculoParaAtualizar.setChassis(chassis);
            return ResponseEntity.ok(veiculoRepository.save(veiculoParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui um veículo por chassis
     * @param chassis do veículo a ser excluído
     * @return uma resposta indicando sucesso ou falha da operação
     */
    @Operation(summary = "Remove o veículo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{chassis}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable String chassis){
        Optional<Veiculo> veiculoPesquisado = veiculoRepository.findById(chassis);

        if(veiculoPesquisado.isPresent()){
            veiculoRepository.delete(veiculoPesquisado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
