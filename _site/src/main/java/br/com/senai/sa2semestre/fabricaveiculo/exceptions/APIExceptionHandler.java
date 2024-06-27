package br.com.senai.sa2semestre.fabricaveiculo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manipulador global de exceções da API
 */
@ControllerAdvice
public class APIExceptionHandler {

    /**
     * Manipula exceções do tipo {@link NotFoundException}.
     * @param ex a exceção a ser manipulada.
     * @return a resposta de erro com a mensagem da exceção.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
