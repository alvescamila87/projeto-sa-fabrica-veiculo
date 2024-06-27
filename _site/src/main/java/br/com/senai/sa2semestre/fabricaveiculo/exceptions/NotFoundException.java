package br.com.senai.sa2semestre.fabricaveiculo.exceptions;

/**
 * Exceção lançada quando um recurso não é encontrado.
 */
public class NotFoundException extends RuntimeException{

    /**
     * Constrói uma nova exceção com a mensagem especificada.
     *
     * @param mensagem a mensagem detalhada da exceção.
     */
    public NotFoundException(String mensagem){
        super(mensagem);
    }
}
