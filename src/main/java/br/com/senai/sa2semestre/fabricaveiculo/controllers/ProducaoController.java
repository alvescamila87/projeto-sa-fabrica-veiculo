package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Producao;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.ProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producao")
public class ProducaoController {

    @Autowired
    private ProducaoRepository producaoRepository;

    /**
     * Obtém todos as produções de montagem cadastradas
     * @return uma lista de produções de montagems cadastradas
     */
    @GetMapping
    public List<Producao> getAllProducoes() {
        return producaoRepository.findAll();
    }

    /**
     * Obtém a produção de montagem por ID fornecido
     * @param id da produção a ser pesquisada
     * @return a produção encontrda de acordo com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producao> getProducaoById(@PathVariable Long id) {
        Optional<Producao> producaoPesquisada = producaoRepository.findById(id);

        if (producaoPesquisada.isPresent()) {
            return ResponseEntity.ok(producaoPesquisada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria uma nova produção de montagem
     * @param producao os dados da nova produção de montagem
     * @return a nova produção de montagem criada
     */
    @PostMapping
    public ResponseEntity<Producao> createProducao(@RequestBody Producao producao) {
        return ResponseEntity.ok(producaoRepository.save(producao));
    }

    /**
     * Atualiza as informaçções de produção da montagem de acordo com ID fornecido
     * @param id de produção para atualizar dados
     * @param producaoParaAtualizar novos dados para atualizar produção da montagem
     * @return a produção com os dados atualizados
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producao> updateProducao(@PathVariable Long id, @RequestBody Producao producaoParaAtualizar) {
        Optional<Producao> producaoPesquisada = producaoRepository.findById(id);

        if (producaoPesquisada.isPresent()){
            producaoParaAtualizar.setIdProducao(id);
        return ResponseEntity.ok(producaoRepository.save(producaoParaAtualizar));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Remove a produção de montagem por ID forneceido
     * @param id´da produco para remover
     * @return mensagem de falha ou sucesso da operação
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducao(@PathVariable Long id){
        Optional<Producao> producaoPesquisada = producaoRepository.findById(id);

        if(producaoPesquisada.isPresent()){
            producaoRepository.delete(producaoPesquisada.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
