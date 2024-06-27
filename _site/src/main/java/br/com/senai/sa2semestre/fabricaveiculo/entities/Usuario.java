package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Representa o usuário de acesso à aplicação que pode ter um perfil para controle de acesso.
 *
 * <p>
 *  A entidade {@code Usuario} está mapeada para a tabela "usuarios" no banco de dados.
 *  </p>
 *
 * @see Perfil
 *
 * @author Camila
 *
 * @since V1
 */

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    /**
     * Construtor padrão da classe {@code Usuario}.
     */
    public Usuario(){}

    /**
     * Construtor completo, com todos os parâmetros para inicializar todos os atributos, a criação de objeto que representa o {@code Usuario}.
     *
     * @param   idUsuario
     *          O ID do usuário de acesso.
     * @param   nome
     *          O nome do usuário: pode ser o nome completo.
     * @param   email
     *          O email do usuário como login de acesso.
     * @param   senha
     *          A senha do usuário de acesso.
     * @param   perfil
     *          O perfil de acesso do usuário que pode ser encontrado em: Perfil.
     *
     * @see Perfil
     */
    public Usuario(Long idUsuario, String nome, String email, String senha, Perfil perfil) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!idUsuario.equals(usuario.idUsuario)) return false;
        if (!Objects.equals(nome, usuario.nome)) return false;
        if (!Objects.equals(email, usuario.email)) return false;
        if (!Objects.equals(senha, usuario.senha)) return false;
        return perfil == usuario.perfil;
    }

    @Override
    public int hashCode() {
        int result = idUsuario.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        result = 31 * result + (perfil != null ? perfil.hashCode() : 0);
        return result;
    }

    /**
     * Exibe todas os atributos de usuário
     * @return Retorna uma representação em string do objeto {@code Usuario}.
     */
    @Override
    public String toString() {
        return "USUÁRIO [" +
                "ID Usuário: " + idUsuario +
                ", Nome: " + nome +
                ", E-mail: " + email +
                ", Senha: " + senha +
                ", Tipo de perfil: " + perfil +
                ']';
    }
}
