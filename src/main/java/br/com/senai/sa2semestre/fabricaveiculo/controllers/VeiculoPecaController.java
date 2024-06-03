package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.VeiculoPeca;
import br.com.senai.sa2semestre.fabricaveiculo.entities.VeiculoPecaId;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.VeiculoPecasRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos-pecas")
public class VeiculoPecaController {

    @Autowired
    private VeiculoPecasRepository veiculoPecasRepository;

    @GetMapping
    public List<VeiculoPeca> getAllVeiculosPecas(){
        return veiculoPecasRepository.findAll();
    }

    @GetMapping("/{chassis}/{idPeca}")
    public ResponseEntity<VeiculoPeca> getVeiculoPecaById(@PathVariable String chassis, @PathVariable Long idPeca){
        VeiculoPecaId idChaveComposta = new VeiculoPecaId(chassis, idPeca);

        Optional<VeiculoPeca> veiculoPecaPesquisado = veiculoPecasRepository.findById(idChaveComposta);

        if(veiculoPecaPesquisado.isPresent()){
            return ResponseEntity.ok(veiculoPecaPesquisado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public VeiculoPeca createVeiculoPeca(@RequestBody VeiculoPeca veiculoPeca) {
        return veiculoPecasRepository.save(veiculoPeca);
    }

    @PutMapping("/{chassis}/{idPeca}")
    public ResponseEntity<VeiculoPeca> updateVeiculoPeca(@PathVariable String chassis, @PathVariable Long idPeca, @RequestBody VeiculoPeca veiculoPecaParaAtualizar){
        VeiculoPecaId idChaveComposta = new VeiculoPecaId(chassis, idPeca);

        Optional<VeiculoPeca> veiculoPecaPesquisado = veiculoPecasRepository.findById(idChaveComposta);

        if (veiculoPecaPesquisado.isPresent()){
            VeiculoPeca veiculoPecaExistente = veiculoPecaPesquisado.get();
            veiculoPecaExistente.setQuantidadePecas(veiculoPecaParaAtualizar.getQuantidadePecas());
            return ResponseEntity.ok(veiculoPecasRepository.save(veiculoPecaParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{chassis}/{idPeca}")
    public ResponseEntity<Void> deleteVeiculoPeca(@PathVariable String chassis, @PathVariable Long idPeca){
        VeiculoPecaId idChaveComposta = new VeiculoPecaId(chassis, idPeca);

        Optional<VeiculoPeca> veiculoPecaPesquisado = veiculoPecasRepository.findById(idChaveComposta);

        if(veiculoPecaPesquisado.isPresent()){
            veiculoPecasRepository.delete(veiculoPecaPesquisado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
