package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Estoque;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de estoque.
 *
 * <p>
 *     Este controlador possui os endpoints para: criar, atualizar, excluir e consultar informações de {@code Estoque}.
 * </p>
 *
 * @see Estoque
 * @see EstoqueRepository
 *
 * @author Camila
 * @since V1
 */

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    /**
     * Obtém todos os estoques.
     * @return uma lista de estoques.
     */
    @GetMapping
    public List<Estoque> getAllEstoques(){
        return estoqueRepository.findAll();
    }

    /**
     * Obtém o estoque por ID especificado
     * @param id do estoque a ser pesquisado
     * @return o estoque de acordo com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable Long id){
        Optional<Estoque> estoquePesquisado = estoqueRepository.findById(id);

        if(estoquePesquisado.isPresent()){
            return ResponseEntity.ok(estoquePesquisado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria um novo estoque
     * @param estoque são os dados do novo estoque
     * @return o novo estoque criado
     */
    @PostMapping()
    public ResponseEntity<Estoque> createEstoque(@RequestBody Estoque estoque){
        return ResponseEntity.ok(estoqueRepository.save(estoque));
    }

    /**
     * Atualiza as informações do estoque de acordo com o ID fornecido
     * @param id do estoque a ser atualizado
     * @param estoqueParaAtualizar são os dados a serem atualizados do estoque
     * @return o estoque atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> updateEstoque(@PathVariable Long id, @RequestBody Estoque estoqueParaAtualizar){
        Optional<Estoque> estoquePesquisado = estoqueRepository.findById(id);

        if(estoquePesquisado.isPresent()){
            estoqueParaAtualizar.setIdEstoque(id);
            return ResponseEntity.ok(estoqueRepository.save(estoqueParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Remove o estoque de acordo com o ID fornecido para deleção
     * @param id do estoque a ser removido
     * @return mensagem de falha ou sucesso da operação
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long id){
        Optional<Estoque> estoquePesquisado = estoqueRepository.findById(id);

        if(estoquePesquisado.isPresent()){
            estoqueRepository.delete(estoquePesquisado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
