package br.com.senai.sa2semestre.fabricaveiculo.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String mensagem){
        super(mensagem);
    }
}
