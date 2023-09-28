package com.kylanches.paineldecontrole.controller;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kylanches.paineldecontrole.model.RespostaErro;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex) {
        RespostaErro respostaErro = new RespostaErro(
                HttpStatus.BAD_REQUEST.value(),
                "O formato da data é inválido. Deve ser YYYY-MM-DD.");
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

}
