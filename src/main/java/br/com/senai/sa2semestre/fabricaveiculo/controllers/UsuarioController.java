package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Usuario;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de usuários.
 *
 * <p>
 *     Este controlador possui os endpoints para: criar, atualizar, excluir e consultar informações de {@code Usuario}.
 * </p>
 *
 * @see Usuario
 * @see UsuarioRepository
 *
 * @author Camila
 * @since V1
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtém todos os usuários.
     * @return uma lista de usuários.
     */
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtém um usuário por ID.
     * @param id o ID do usuário.
     * @return o usuário com o ID especificado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
        Optional<Usuario> usuarioPesquisado = usuarioRepository.findById(id);

        if(usuarioPesquisado.isPresent()){
            return ResponseEntity.ok(usuarioPesquisado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cria um novo usuário.
     * @param usuario dados do novo usuário.
     * @return o novo usuário criado.
     */
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    /**
     * Atualiza um usuário existente.
     * @param id o ID do usuário a ser atualizado.
     * @param usuarioParaAtualizar os novos dados do usuário.
     * @return o usuário atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioParaAtualizar){
        Optional<Usuario> usuarioPesquisado = usuarioRepository.findById(id);

        if(usuarioPesquisado.isPresent()){
            usuarioParaAtualizar.setIdUsuario(id);
            return ResponseEntity.ok(usuarioRepository.save(usuarioParaAtualizar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui um usuário por ID.
     * @param id o ID do usuário a ser excluído.
     * @return uma resposta indicando o sucesso ou falha da operação.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioPesquisado = usuarioRepository.findById(id);

        if(usuarioPesquisado.isPresent()){
            usuarioRepository.delete(usuarioPesquisado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
