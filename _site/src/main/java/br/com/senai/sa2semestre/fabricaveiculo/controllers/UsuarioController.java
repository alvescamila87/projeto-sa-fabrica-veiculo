package br.com.senai.sa2semestre.fabricaveiculo.controllers;

import br.com.senai.sa2semestre.fabricaveiculo.entities.Usuario;
import br.com.senai.sa2semestre.fabricaveiculo.exceptions.NotFoundException;
import br.com.senai.sa2semestre.fabricaveiculo.repositories.UsuarioRepository;
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
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtém todos os usuários.
     * @return uma lista de usuários.
     */
    @Operation(summary = "Obtém todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários obtida com sucesso", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtém um usuário por ID.
     * @param id o ID do usuário.
     * @return o usuário com o ID especificado.
     * @throws NotFoundException se o usuário com o ID especificado não for encontradp.
     */
    @Operation(summary = "Obtém o usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
        Optional<Usuario> usuarioPesquisado = usuarioRepository.findById(id);

        if(usuarioPesquisado.isPresent()){
            return ResponseEntity.ok(usuarioPesquisado.get());
        } else {
            throw new NotFoundException("Usuário não encontrado com ID: " + id);
        }
    }

    /**
     * Cria um novo usuário.
     * @param usuario dados do novo usuário.
     * @return o novo usuário criado.
     */
    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    /**
     * Atualiza um usuário existente.
     * @param id o ID do usuário a ser atualizado.
     * @param usuarioParaAtualizar os novos dados do usuário.
     * @return o usuário atualizado.
     * @throws NotFoundException se o usuário com o ID especificado não for encontradp.
     */
    @Operation(summary = "Atualiza as informações do usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioParaAtualizar){
        Optional<Usuario> usuarioPesquisado = usuarioRepository.findById(id);

        if(usuarioPesquisado.isPresent()){
            usuarioParaAtualizar.setIdUsuario(id);
            return ResponseEntity.ok(usuarioRepository.save(usuarioParaAtualizar));
        } else {
            throw new NotFoundException("Usuário não encontrado com ID: " + id);
        }
    }

    /**
     * Exclui um usuário por ID.
     * @param id o ID do usuário a ser excluído.
     * @return uma resposta indicando o sucesso ou falha da operação.
     * @throws NotFoundException se o usuário com o ID especificado não for encontradp.
     */
    @Operation(summary = "Remove o usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioPesquisado = usuarioRepository.findById(id);

        if(usuarioPesquisado.isPresent()){
            usuarioRepository.delete(usuarioPesquisado.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new NotFoundException("Usuário não encontrado com ID: " + id);
        }
    }
}
