package br.com.senai.sa2semestre.fabricaveiculo.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Representa o usuário de acesso
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

    public Usuario(){

    }
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
     * @return Retorna uma representação em string do objeto Usuário
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
